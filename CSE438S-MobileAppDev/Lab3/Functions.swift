//
//  Functions.swift
//  Lab3
//
//  Created by Nikki Wines on 2/10/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class Functions {
    
    private static func midpoint(first: CGPoint, second: CGPoint) -> CGPoint {
        let midx = (first.x + second.x) / 2
        let midy = (first.y + second.y) / 2
        let mid = CGPoint(x: midx, y: midy)
        return mid
    }
    
    static func createQuadPath(points: [CGPoint]) -> UIBezierPath {
        let path = UIBezierPath()
        if points.count < 2 { return path }
        let firstPoint = points[0]
        let secondPoint = points[1]
        let firstMidpoint = midpoint(first: firstPoint, second: secondPoint)
        path.move(to: firstPoint)
        path.addLine(to: firstMidpoint)
        for index in 1 ..< points.count-1 {
            let currentPoint = points[index]
            let nextPoint = points[index + 1]
            let midPoint = midpoint(first: currentPoint, second: nextPoint)
            path.addQuadCurve(to: midPoint, controlPoint: currentPoint)
        }
        guard let lastLocation = points.last else { return path }
        path.addLine(to: lastLocation)
        return path
    }
}
