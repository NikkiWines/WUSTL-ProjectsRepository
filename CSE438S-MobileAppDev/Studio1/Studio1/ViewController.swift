//
//  ViewController.swift
//  Studio1
//
//  Created by Nikki Wines on 1/27/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var photoView: UIImageView!
    
    @IBAction func sliderChanged(_ sender: UISlider) {
        photoView.alpha = CGFloat(sender.value)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        photoView.image = UIImage(named: "brookings-seal")

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

