//
//  DrawingView.swift
//  Lab3
//
//  Created by Nikki Wines on 2/15/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class DrawingView: UIView {

    override func draw(_ rect: CGRect) {
        
        for thisLine in allLines {
            let thisLinePath = thisLine.thisPath
            thisLinePath.lineWidth = thisLine.brushSize
            thisLine.brushColor.setStroke()
            thisLinePath.stroke()

        }
        currentBrushColor.setStroke()
        currentBezierPath.lineWidth = CGFloat(currentBrushSize)
        currentBezierPath.stroke()
        
    }
    
    override init (frame : CGRect) {
        super.init(frame : frame)
    }
    convenience init () {
        self.init(frame:CGRect.zero)
        self.backgroundColor = UIColor.white
    }
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
    }

}
