//
//  ViewController.swift
//  Lab2
//
//  Created by Nikki Wines on 2/6/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var playButton: UIButton!
    @IBOutlet var feedButton: UIButton!
    @IBOutlet var happinessLabel: UILabel!
    @IBOutlet var foodLevelLabel: UILabel!
    @IBOutlet var happinessView: DisplayView!
    @IBOutlet var foodLevelView: DisplayView!
    @IBOutlet var iconView: UIImageView!
    @IBOutlet var backgroundView: UIView!
    @IBOutlet var dogButton: UIButton!
    @IBOutlet var iconLeft: UIImageView!
    
    var pets = [Pet(species: .dog), Pet(species: .cat), Pet(species: .bird), Pet(species: .bunny), Pet(species: .fish)]
    
    var currentPet:Pet! { 
        didSet {
            updateView()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        dogPressed(dogButton)
        
    }
    //play/feed button presses
    @IBAction func playPressed(_ sender: UIButton) {
        if (Int(foodLevelLabel.text!)! > 0) {
        currentPet.play()
        updateView()
        }
    }
    @IBAction func feedPressed(_ sender: UIButton) {
        currentPet.feed()
        updateView()
    }
    //pet Change button press
    @IBAction func dogPressed(_ sender: UIButton) {
        petChanged(petNum: 0, petName: "dog", petColor: .red)

    }
    @IBAction func catPressed(_ sender: UIButton) {
        petChanged(petNum: 1, petName: "cat", petColor: .blue)
    }
    @IBAction func birdPressed(_ sender: UIButton) {
        petChanged(petNum: 2, petName: "bird", petColor: .yellow)
    }
    @IBAction func bunnyPressed(_ sender: UIButton) {
        petChanged(petNum: 3, petName: "bunny", petColor: .orange)
    }
    @IBAction func fishPressed(_ sender: UIButton) {
        petChanged(petNum: 4, petName: "fish", petColor: .magenta)
    }
    
    // view updating functions
    private func petChanged(petNum: Int, petName: String, petColor: UIColor) {
        currentPet = pets[petNum]
        iconView.image = UIImage(named: petName)
        backgroundView.backgroundColor = petColor
        happinessView.color = petColor
        foodLevelView.color = petColor
        
        updateView()
    }
    private func updateView() {
        
        happinessLabel.text = String(currentPet.happiness)
        foodLevelLabel.text = String(currentPet.foodLevel)
        
        let happinessValue = Double(currentPet.happiness)/10
        let foodValue = Double(currentPet.foodLevel)/10
        
        happinessView.animateValue(to: CGFloat(happinessValue))
        foodLevelView.animateValue(to: CGFloat(foodValue))
        
        if (currentPet.foodLevel == 0 ) {
            iconLeft.image = UIImage(named: "feedme")

        }
        else if (currentPet.foodLevel >= 20) {
           iconLeft.image = UIImage(named: "imtoofull")
        }
        else {
            iconLeft.image = UIImage(named: "immtoofull");
        }
        if (currentPet.happiness >= 20 ) {
            iconLeft.image = UIImage(named: "imsohappy")
        }


    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

