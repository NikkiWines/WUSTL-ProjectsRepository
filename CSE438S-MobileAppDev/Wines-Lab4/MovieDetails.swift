//
//  MovieDetails.swift
//  Lab4
//
//  Created by Nikki Wines on 2/28/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class MovieDetails: UIViewController {
    
    var movImage:UIImage!
    var movName:String!
    var movDescription:String!
    //var favMovieImages: [UIImage] = []
    
    @IBOutlet var movieImage: UIImageView!
    @IBOutlet var movieLabel: UILabel!
    @IBOutlet var movieDescription: UILabel!
    
    @IBAction func addToFavoritesPressed(_ sender: UIButton) {
        let userDefaults = UserDefaults.standard
        
        var favoriteMovies = userDefaults.array(forKey: "Favorites") as? [String] ?? [String]()
        favoriteMovies.append(movName)
        userDefaults.set(favoriteMovies, forKey: "Favorites")
        userDefaults.synchronize() // don't forget this!!!!
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        movieImage.image = movImage
        movieLabel.text  = movName
        movieDescription.text = movDescription
        self.title = movName
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
}
