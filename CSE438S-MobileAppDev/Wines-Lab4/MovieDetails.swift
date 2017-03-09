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
    var movIMDB:String!
    var favMovs: [String] = []

    
    @IBOutlet var movieImage: UIImageView!
    @IBOutlet var movieLabel: UILabel!
    @IBOutlet var movieDesc: UILabel!
    @IBOutlet var movieRating: UILabel!
    @IBOutlet var movieYear: UILabel!
    @IBOutlet var favoritesButton: UIButton!
    
    
    @IBAction func addToFavoritesPressed(_ sender: UIButton) {
        let userDefaults = UserDefaults.standard
        favMovs = userDefaults.array(forKey: "Favorites") as? [String] ?? [String]()
        favMovs.append(movName)
        favoritesButton.setImage(UIImage(named: "star-fill"), for:.normal)
        favoritesButton.isEnabled = false
        userDefaults.set(favMovs, forKey: "Favorites")
        
        /* alert code from: http://www.learnswiftonline.com/reference-guides/uialertcontroller/ */
        let alert = UIAlertController(title: "Favorited!", message: "You've favorited \(movName ?? "")", preferredStyle: .alert)
        let OKPressed = UIAlertAction(title: "OK", style: .default) { (action:UIAlertAction) in
        }
        
        alert.addAction(OKPressed)
        self.present(alert, animated: true, completion: nil)
    }
    func favorited() {
        let userDefaults = UserDefaults.standard
        favMovs = userDefaults.array(forKey: "Favorites") as? [String] ?? [String]()
        if favMovs.contains(movName) {
            print("Movie already favorited")
            favoritesButton.setImage(UIImage(named: "star-fill"), for:.normal)
            favoritesButton.isEnabled = false
        }
        else {
            favoritesButton.setImage(UIImage(named: "star-empty"), for:.normal)
            favoritesButton.isEnabled = true


        }
    }
    
    private func getJSON( url: String) -> JSON {
        if let url = URL(string: url) {
            if let data = try? Data(contentsOf: url) {
                let json = JSON(data: data)
                return json
            }
            else {
                return JSON.null
            }
        }
        else {
            return JSON.null
        }
    }

    private func fetchDataForDetailView() {
        // test from class adapt to actual lab
        
        let json = getJSON(url: "http://www.omdbapi.com/?i=\(movIMDB ?? "")")
        movieDesc.text = json["Plot"].stringValue
        movieRating.text = "Rating: \(json["imdbRating"].stringValue)"
        movieYear.text = "Released: \(json["Released"].stringValue)"

    }

    override func viewDidLoad() {
        super.viewDidLoad()
        fetchDataForDetailView()
        favorited()
        movieImage.image = movImage
        movieLabel.text  = movName
        self.title = movName

        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        favorited()

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
