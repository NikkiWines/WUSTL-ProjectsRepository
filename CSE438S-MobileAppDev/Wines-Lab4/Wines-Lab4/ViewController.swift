//
//  ViewController.swift
//  Wines-Lab4
//
//  Created by Nikki Wines on 2/28/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//
import UIKit

class ViewController: UIViewController,UICollectionViewDataSource, UICollectionViewDelegate, UISearchBarDelegate{
    
    var movieData: [Movie] = []
    var imageCache : [UIImage] = []
    var searchActive = false
    var filtered:[String] = []
    var nameArray:[String] = []
    
    @IBOutlet var theCollectionView: UICollectionView!
    @IBOutlet var searchBar: UISearchBar!
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movieData.count
        
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath)  {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mycell", for: indexPath)
        cell.isSelected = true
        let movieDetailsVC = MovieDetails(nibName: "MovieDetails", bundle: nil )
        movieDetailsVC.movName = movieData[indexPath.row].name
        movieDetailsVC.movDescription = movieData[indexPath.row].description
        movieDetailsVC.movImage = imageCache[indexPath.row]
        navigationController?.pushViewController(movieDetailsVC, animated: true)
        
}

    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mycell", for: indexPath)
        
        let imageView:UIImageView = UIImageView()
        imageView.frame = CGRect(x: 0, y: 0, width: 85, height: 100)
        imageView.image = imageCache[indexPath.row]
        cell.addSubview(imageView)
        
        return cell
    }
    
    //fetch data for collection view
    
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
    
    private func fetchDataForCollectionView() {
        // test from class adapt to actual lab
        let json = getJSON(url: "http://research.engineering.wustl.edu/~todd/studio.json");
        
        for result in json.arrayValue {
            let name = result["name"].stringValue
            let description = result["description"].stringValue
            let url = result["image_url"].stringValue
            movieData.append(Movie(name:name, description:description, url:url))
            nameArray.append(name)
        }
        
    }
    
    //cache images
    private func cacheImages() {
        
        for movie in movieData {
            let url = URL(string: movie.url)
            let data = try? Data(contentsOf: url!)
            let image = UIImage(data: data!)
            imageCache.append(image!)
        }
    }
    
    // searchbar 
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        searchActive = true;
    }
   func searchBarTextDidEndEditing(_ searchBar: UISearchBar) {
        searchActive = false;
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        searchActive = false;
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchActive = false;
    }

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
       
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        theCollectionView.dataSource = self
        theCollectionView.delegate = self
        searchBar.delegate = self
        // Do any additional setup after loading the view, typically from a nib.
        DispatchQueue.global(qos : .userInitiated).async {
            self.fetchDataForCollectionView()
            self.cacheImages()
            DispatchQueue.main.async {
                self.theCollectionView.reloadData()
            }
        }


        self.title = "Movies"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}


