//
//  ViewController.swift
//  VirtualPetApp
//
//  Created by Nathan Gitter on 11/8/16.
//  Copyright © 2016 Nathan Gitter. All rights reserved.
//

import UIKit
import WatchConnectivity
import AVFoundation

class ViewController: UIViewController {
    
    
    var currentPet: Pet! {
        didSet {
            
            petImageView.image = currentPet.image
            petView.backgroundColor = currentPet.color
            happinessDisplayView.color = currentPet.color
            hungrinessDisplayView.color = currentPet.color
            updateDisplayViews(animated: false)
            
        }
    }
    
    @IBOutlet weak var petView: UIView!
    @IBOutlet weak var petImageView: UIImageView!

    @IBOutlet weak var happinessLabel: UILabel!
    @IBOutlet weak var hungrinessLabel: UILabel!
    
    @IBOutlet weak var happinessDisplayView: DisplayView!
    @IBOutlet weak var hungrinessDisplayView: DisplayView!
    
    var petSound: AVAudioPlayer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        currentPet = pets[.Dog]
        updateDisplayViews(animated: false)
    }
    
    private let session: WCSession? = WCSession.isSupported() ? WCSession.default() : nil
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        configureWCSession()
    }
    
    fileprivate func configureWCSession() {
        session?.delegate = self;
        session?.activate()
    }
    

    func updateDisplayViews(animated: Bool) {
        let happinessDisplayValue = CGFloat(currentPet.happiness) / 10
        let hungrinessDisplayValue = CGFloat(currentPet.foodLevel) / 10
        if animated {
            happinessDisplayView.animateValue(to: happinessDisplayValue)
            hungrinessDisplayView.animateValue(to: hungrinessDisplayValue)
            
        } else {
            happinessDisplayView.value = happinessDisplayValue
            hungrinessDisplayView.value = hungrinessDisplayValue
        }
        happinessLabel.text = "played: \(currentPet.playSessions)"
        hungrinessLabel.text = "fed: \(currentPet.feedings)"
    }
    

    func stringToType(petName: String)-> Pet?{
        var pet = pets[.Dog]
        switch petName {
        case "Dog":
            pet = pets[.Dog]
        case "Cat":
            pet = pets[.Cat]
        case "Bird":
            pet = pets[.Bird]
        case "Bunny":
            pet = pets[.Bunny]
        case "Fish":
            pet = pets[.Fish]
        default:
            pet = pets[.Dog]
        }
        return pet
    }

    
    // MARK: Interaction Button Actions

    @IBAction func feedButtonPressed(_ sender: UIButton) {
        currentPet.feed()
        updateDisplayViews(animated: true)


        
    }
    
    @IBAction func playButtonPressed(_ sender: UIButton) {
        currentPet.play()
        updateDisplayViews(animated: true)
        
    }
    
    // MARK: Animal Button Actions
    
    @IBAction func dogButtonPressed(_ sender: UIButton) {
        updateDisplayViews(animated: true)
        currentPet = pets[.Dog]
    }
    
    @IBAction func catButtonPressed(_ sender: UIButton) {
        currentPet = pets[.Cat]
    }
    
    @IBAction func birdButtonPressed(_ sender: UIButton) {
        currentPet = pets[.Bird]
    }
    
    @IBAction func bunnyButtonPressed(_ sender: UIButton) {
        currentPet = pets[.Bunny]
        
    }
    
    @IBAction func fishButtonPressed(_ sender: UIButton) {
        currentPet = pets[.Fish]
        
    }
    
    
}
extension ViewController: WCSessionDelegate {

    public func sessionDidDeactivate(_ session: WCSession) {
    }
    

    public func sessionDidBecomeInactive(_ session: WCSession) {
    }
    

    public func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) {
    }
    
    
    func session(_ session: WCSession, didReceiveMessage message: [String : Any], replyHandler: @escaping ([String : Any]) -> Void) {

        
        if let msgPet = message["CurrentPetFood"] {
        DispatchQueue.main.async {
            let altPet = self.stringToType(petName: msgPet as! String)
            altPet?.feedings += 1;
            //alert message
            let alert = UIAlertController(title: "\((altPet?.type)!) has been fed", message: "Food Level: \((altPet?.foodLevel)!)", preferredStyle: .alert)
            let OKPressed = UIAlertAction(title: "OK", style: .default) { (action:UIAlertAction) in }
            alert.addAction(OKPressed)
            self.present(alert, animated: true, completion: nil)
            self.updateDisplayViews(animated: true)
            }

        }
        if let msgPet = message["CurrentPetPlay"] {
            DispatchQueue.main.async {
                let altPet = self.stringToType(petName: msgPet as! String)
                if ((altPet?.foodLevel)! > 0) {
                altPet?.playSessions += 1;
                //alert message
                let alert = UIAlertController(title: "\((altPet?.type)!) has been played with", message: "Happiness Level: \((altPet?.happiness)!)", preferredStyle: .alert)
                let OKPressed = UIAlertAction(title: "OK", style: .default) { (action:UIAlertAction) in }
                alert.addAction(OKPressed)
                self.present(alert, animated: true, completion: nil)
                }
                self.updateDisplayViews(animated: true)

            }

        }
        if let pet = message["Sound"] {
            let url = Bundle.main.url(forResource: "\(pet)", withExtension: "wav")!
            do {
                petSound = try AVAudioPlayer(contentsOf: url)
                guard let petSound = petSound else { return }
                
                petSound.prepareToPlay()
                petSound.play()
            } catch let error {
                print(error.localizedDescription)
            }
        }
    }
}



