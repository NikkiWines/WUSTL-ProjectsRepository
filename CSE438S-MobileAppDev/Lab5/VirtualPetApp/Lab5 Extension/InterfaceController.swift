//
//  InterfaceController.swift
//  Lab5 Extension
//
//  Created by Nikki Wines on 3/26/17.
//  Copyright © 2017 Nathan Gitter. All rights reserved.
//

import WatchKit
import Foundation
import WatchConnectivity


class InterfaceController: WKInterfaceController, WCSessionDelegate {
    @IBOutlet var petImage: WKInterfaceButton!
    
    @IBOutlet var playBtn: WKInterfaceButton!
    @IBOutlet var foodBtn: WKInterfaceButton!
    
    // sessions code
    public func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) {
    }
    
    private var session : WCSession? = WCSession.isSupported() ? WCSession.default() : nil
    
    
    override init() {
        super.init()
        
        session?.delegate = self
        session?.activate()
    }

    
    override func willActivate() {
        super.willActivate()
        petImage.setBackgroundImage(currentPet.image)
        playBtn.setBackgroundColor(currentPet.color)
        foodBtn.setBackgroundColor(currentPet.color)

    }
    
    override func awake(withContext context: Any?) {
        super.awake(withContext: context)
        // Configure interface objects here.
    }

    override func didDeactivate() {
        // This method is called when watch view controller is no longer visible
        super.didDeactivate()
    }

    
    // buttons
    @IBAction func feedPressed() {
        let currPet = String(describing: currentPet.type)
        let message = ["CurrentPetFood": currPet] as [String : String]
        if let session = session, session.isReachable {
            session.sendMessage(message,
                                replyHandler: { reply in
                                    // handle reply from iPhone app here
                                    print(reply)
            }, errorHandler: { error in
                // catch any errors here
                print(error)
            })
        } else {
            // do nothing
        }

    }
    @IBAction func playPressed() {
        let currPet = String(describing: currentPet.type)
        let message = ["CurrentPetPlay": currPet] as [String : String]
        if let session = session, session.isReachable {
            session.sendMessage(message,
                                replyHandler: { reply in
                                    // handle reply from iPhone app here
                                    print(reply)
            }, errorHandler: { error in
                // catch any errors here
                print(error)
            })
        } else {
            // do nothing
        }
    }
    
    // playing sound


    @IBAction func petImagePressed() {
        let currPet = String(describing: currentPet.type)
        let message = ["Sound": currPet] as [String : String]
        if let session = session, session.isReachable {
            session.sendMessage(message,
                                replyHandler: { reply in
                                    // handle reply from iPhone app here
                                    print(reply)
            }, errorHandler: { error in
                // catch any errors here
                print(error)
            })
        } else {
            // do nothing
        }
    }
    
    // swipe mech 
    
    // tw ations so its a little more geneous with the gestures.
    @IBAction func swipedOnIcon(_ sender: Any) {
        swipeNextPet()
    }

    @IBAction func swipedOnImage(_ sender: Any) {
        swipeNextPet()
    }

    
    func swipeNextPet() {
        var petName = "Dog"
        var petIndex = 0;
        let currPet = String(describing: currentPet.type)
        for i in 0...petArr.count-1 {
            if petArr[i] == currPet {
                petIndex = i
            }
        }
        if (petIndex != petArr.count - 1) {
            petName = petArr[petIndex + 1]
        }
        else {
            petName = petArr[0]
        }
        stringToPet(petName: petName)
        petImage.setBackgroundImage(currentPet.image)
        playBtn.setBackgroundColor(currentPet.color)
        foodBtn.setBackgroundColor(currentPet.color)

    }
   

    
}
