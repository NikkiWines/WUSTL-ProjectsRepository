//
//  Pet.swift
//  Lab2
//
//  Created by Nikki Wines on 2/5/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import Foundation

class Pet {
    
    enum Species {
        case dog
        case cat
        case bird
        case bunny
        case fish
    }
    
    //Data
    private (set) var happiness:Int
    private (set) var foodLevel:Int
    var speciesType:Species
    
    
    //Behavior
    func play() {
        happiness += 1
        foodLevel -= 1
        if(foodLevel < 0) {
            foodLevel = 0
        }
    }
    
    func feed() {
        foodLevel += 1
    }
    
    init( species: Species) {
        self.speciesType = species
        happiness = 5
        foodLevel = 5
    }
    
}
