//
//  PetExtensions.swift
//  VirtualPetApp
//
//  Created by Nathan Gitter on 11/8/16.
//  Copyright © 2016 Nathan Gitter. All rights reserved.
//

import UIKit

extension Pet {
    
    var color: UIColor {
        switch self.type {
        case .Dog:
            return UIColor(red: 255/255, green: 80/255, blue: 80/255, alpha: 1)
        case .Cat:
            return UIColor(red: 74/255, green: 144/255, blue: 250/255, alpha: 1)
        case .Bird:
            return UIColor(red: 255/255, green: 219/255, blue: 73/255, alpha: 1)
        case .Bunny:
            return UIColor(red: 109/255, green: 219/255, blue: 119/255, alpha: 1)
        case .Fish:
            return UIColor(red: 231/255, green: 158/255, blue: 255/255, alpha: 1)
        }
    }
    
}

extension Pet {
    
    var image: UIImage? {
        switch self.type {
        case .Dog:
            return UIImage(named: "dog")
        case .Cat:
            return UIImage(named: "cat")
        case .Bird:
            return UIImage(named: "bird")
        case .Bunny:
            return UIImage(named: "bunny")
        case .Fish:
            return UIImage(named: "fish")
            
        }
    }
    
}
