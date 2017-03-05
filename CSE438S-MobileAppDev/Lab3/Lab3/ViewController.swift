//
//  ViewController.swift
//  Lab3
//
//  Created by Nikki Wines on 2/10/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

var allLines = [Line] ()
var currentAlpha = 0.5
var currentBezierPath = UIBezierPath()
var currentBrushColor = UIColor.black.withAlphaComponent(CGFloat(currentAlpha))
var currentBrushSize = 10.0
let dV = DrawingView()


class ViewController: UIViewController {
    
    var pointsForLine = [CGPoint] ()
    var lastPoint = CGPoint.zero
    var currentLine = Line()
    var isMoved = false;

    @IBOutlet var sizeIcon: UIImageView!
    @IBOutlet var alphaIcon: UIImageView!
    
    
    //std fxns
    override func viewDidLoad() {
        super.viewDidLoad()
        dV.frame = self.view.bounds
        view.addSubview(dV)
        view.sendSubview(toBack: dV)
        alphaIcon.image = UIImage(named: "opacityblack")
        sizeIcon.image = UIImage(named: "sizeblack")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    //top view interactions: switch, sliders, undo, and clear
    @IBAction func clearPressed(_ sender: UIButton) {
        allLines.removeAll()
        pointsForLine.removeAll()
        currentLine = Line()
        currentBezierPath = UIBezierPath()
        lastPoint = CGPoint.zero
        dV.setNeedsDisplay()
    }
    @IBAction func undoPressed(_ sender: UIButton) {
        if (allLines.count > 0) {
        allLines.removeLast()
        pointsForLine.removeAll()
        currentLine = Line()
        currentBezierPath = UIBezierPath()
        lastPoint = CGPoint.zero
        dV.setNeedsDisplay()
        }
    }
    @IBAction func switchFlip(_ sender: UISwitch) {
        if (!sender.isOn) {
            dV.backgroundColor = .white
            alphaIcon.image = UIImage(named: "opacityblack")
            sizeIcon.image = UIImage(named: "sizeblack")
            
        }
        else {
            dV.backgroundColor = .black
            alphaIcon.image = UIImage(named: "opacitywhite")
            sizeIcon.image = UIImage(named: "sizewhite")
        }
    }
    
    @IBAction func alphaSliderMoved(_ sender: UISlider) {
        currentAlpha = Double(sender.value)
        currentBrushColor = currentBrushColor.withAlphaComponent(CGFloat(currentAlpha))
    }
    @IBAction func sizeSliderMoved(_ sender: UISlider) {
        currentBrushSize = Double(sender.value)
    }
    
    // buttonColors changed
    @IBAction func redPressed(_ sender: UIButton) {
        currentBrushColor = sender.backgroundColor!.withAlphaComponent(CGFloat(currentAlpha))
    }
   
    // touches functions
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard
            let touchPoint = touches.first?.location(in: view) else { return }
        
        isMoved = false;

        pointsForLine.removeAll()
        pointsForLine.append(touchPoint)
        lastPoint = touchPoint

        currentLine = Line()
        currentLine.brushSize = CGFloat(currentBrushSize);
        currentLine.brushColor = currentBrushColor.withAlphaComponent(CGFloat(currentAlpha));
        currentBezierPath = Functions.createQuadPath(points: pointsForLine)
        currentLine.thisPath = currentBezierPath
        
        dV.setNeedsDisplay()
    }

    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard
            let touchPoint = touches.first?.location(in: view) else { return }
        
        isMoved = true;

        pointsForLine.append(touchPoint)
        lastPoint = touchPoint
        
        currentBezierPath = Functions.createQuadPath(points: pointsForLine)
        currentLine.thisPath = currentBezierPath
        dV.setNeedsDisplay()

    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        if (!isMoved)
        {
            var dot: UIBezierPath
            let x = Double(lastPoint.x) - (currentBrushSize/2)
            let y = Double(lastPoint.y) - (currentBrushSize/2)

            let rect = CGRect(x: x, y: y, width: currentBrushSize, height: currentBrushSize)
            dot = UIBezierPath(ovalIn: rect)
            dot.lineWidth = CGFloat(currentBrushSize)
            currentBezierPath = dot
            currentLine.thisPath = currentBezierPath
            dV.setNeedsDisplay()
        }
        allLines.append(currentLine)
        
        dV.setNeedsDisplay()
    }
}

