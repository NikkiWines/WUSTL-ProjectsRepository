//
//  ViewController.swift
//  Lab1
//
//  Created by Nikki Wines on 1/26/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    //vars
    @IBOutlet var iconView: UIImageView!
    @IBOutlet var originalPriceField: UITextField!
    @IBOutlet var discountField: UITextField!
    @IBOutlet var taxField: UITextField!
    @IBOutlet var finalPriceDisplay: UILabel!
    @IBOutlet var amountSavedDisplay: UILabel!
    @IBOutlet var priceStepper: UIStepper!
    @IBOutlet var discountStepper: UIStepper!
    @IBOutlet var taxStepper: UIStepper!
    
    //action fxns
    // textboxes
    @IBAction func priceChanged(_ sender: UITextField) {
        // found solution for setting default stepper value from this stackoverflow link: http://stackoverflow.com/questions/35976596/how-can-i-change-uistepper-value-when-changed-from-uitextfield
        // this fxn will be repeated for each of the textboxes -- next 2 action fxns
        if Double(originalPriceField.text!) != nil {
            priceStepper.value = Double(originalPriceField.text!)!
        }
        // end of stackoverflow related code for this fxn
        somethingChanged()
        
    }
    @IBAction func discountChanged(_ sender: UITextField) {
        if Double(discountField.text!) != nil {
            discountStepper.value = Double(discountField.text!)!
        }
       somethingChanged()
    }
    @IBAction func taxChanged(_ sender: UITextField) {
        if Double(taxField.text!) != nil {
            taxStepper.value = Double(taxField.text!)!
        }
       somethingChanged()
    }
    
    //steppers
    @IBAction func priceStepperChanged(_ sender: UIStepper) {
        // see comment undernead func priceChanged(...) - the line of code below is also from this same stackoverflow link.
        // this line of code will also be repeated for the next 2 action fxns
        originalPriceField.text = String(priceStepper.value)
        //end of stackoverflow related code for this fxn
        somethingChanged()
    }
    @IBAction func discountStepperChanged(_ sender: UIStepper) {
        discountField.text = String(discountStepper.value)
        somethingChanged()

    }
    @IBAction func taxStepperChanged(_ sender: UIStepper) {
        taxField.text = String(taxStepper.value)
        somethingChanged()

    }
    
    func somethingChanged() {
        //unwrapping
        var price = Double(originalPriceField.text!)
        var discount = Double(discountField.text!)
        var taxes = Double(taxField.text!)
        
        // edge cases
        if (price == nil) { price = 0}
        if (discount == nil) {discount = 0}
        if (taxes == nil) {taxes = 0}
        
        if ((price! < 0) || (originalPriceField.text == "-")){price = 0}
        if ((discount! < 0) || (discountField.text == "-")){discount = 0}
        if ((taxes! < 0) || (taxField.text == "-")){taxes = 0}
        
        //calculations
        let disc = ( 100 - discount! ) / 100
        let dprice = price! * disc
        let tax = (dprice * taxes!) / 100
        let final = dprice + tax // total price: discount price + (discount price * sales tax)
        finalPriceDisplay.text =  "$\(String(format: "%.2f", final))"
        
        // amount saved
        let difference =  round(100 * (price! - final)) / 100
        if ( difference < 0.00) { amountSavedDisplay.textColor = .red }
        else if (difference > 0.00) { amountSavedDisplay.textColor = .green}
        else {amountSavedDisplay.textColor = .black}
        amountSavedDisplay.text = "$\(String(format: "%.2f", difference))"
    }
    
    // preset fxns
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        //load icon
        iconView.image = UIImage(named: "cart")
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

