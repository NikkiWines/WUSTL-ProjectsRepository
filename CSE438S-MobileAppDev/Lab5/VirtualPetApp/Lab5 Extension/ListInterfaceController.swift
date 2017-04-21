//
//  ListInterfaceController.swift
//  VirtualPetApp
//
//  Created by Nikki Wines on 3/26/17.
//  Copyright © 2017 Nathan Gitter. All rights reserved.
//

import WatchKit

var currentPet: Pet!

// string to pet fxn

func stringToPet(petName: String) {
    switch petName {
    case "Dog":
        currentPet = pets[.Dog]
    case "Cat":
        currentPet = pets[.Cat]
    case "Bird":
        currentPet = pets[.Bird]
    case "Bunny":
        currentPet = pets[.Bunny]
    case "Fish":
        currentPet = pets[.Fish]
    default:
        currentPet = pets[.Dog]
    }
}

var petArr: [String] = []
class ListInterfaceController: WKInterfaceController {
    
    @IBOutlet var table: WKInterfaceTable!

    
    
    override func awake(withContext context: Any?) {
        super.awake(withContext: context)

        table.setNumberOfRows(pets.count, withRowType: "myrow")
        for (index, word) in pets.enumerated() {
            guard let row = table.rowController(at: index) as? MyRowController else { return }
            row.text = String(describing: word.key)
            petArr.append(row.text)
        }

    }

    
    override func table(_ table: WKInterfaceTable, didSelectRowAt rowIndex: Int) {
        guard let row = table.rowController(at: rowIndex) as? MyRowController else { return }
        stringToPet(petName: row.text)
        self.pushController(withName: "PetDetail", context: currentPet)
    }
    
    
    
}

class MyRowController: NSObject {
    
    @IBOutlet var label: WKInterfaceLabel!
    
    public var text: String = "" {
        didSet {
            label.setText(text)
        }
    }
    
}

