//
//  ViewController.swift
//  Wines-Lab4
//
//  Created by Nikki Wines on 2/28/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//
import UIKit

class ViewController: UIViewController,UICollectionViewDataSource, UICollectionViewDelegate, UISearchBarDelegate{
    // sorting map - can sort associated array .
    var movieData: [Movie] = []
    var imageCache : [UIImage] = []
    var searchActive = false
    var nameArray:[String] = []
    
    @IBOutlet var theCollectionView: UICollectionView!
    @IBOutlet var searchBar: UISearchBar!
    @IBOutlet var activityIndicator: UIActivityIndicatorView!
    @IBOutlet var errorLabel: UILabel!
    @IBOutlet var errorIcon: UIImageView!
    @IBOutlet var AZbutton: UIButton!
    
    @IBAction func AZSelected(_ sender: Any) {
        imageCache.removeAll()
        movieData = movieData.sorted { $0.name < $1.name }
        for i in 0  ..< movieData.count {
            nameArray[i] = movieData[i].name
        }
        self.cacheImages()
        self.theCollectionView.reloadData()
    }
    
    @IBAction func ZASelected(_ sender: Any) {
        imageCache.removeAll()
        movieData = movieData.sorted { $1.name < $0.name }
        for i in 0  ..< movieData.count {
            nameArray[i] = movieData[i].name
        }
        self.cacheImages()
        self.theCollectionView.reloadData()
    }
    
    @IBAction func releasedSelected(_ sender: Any) {
        imageCache.removeAll()
        movieData = movieData.sorted { $1.year < $0.year }
        for i in 0  ..< movieData.count {
            nameArray[i] = movieData[i].name
        }
        self.cacheImages()
        self.theCollectionView.reloadData()
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movieData.count
        
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath)  {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mycell", for: indexPath)
        cell.isSelected = true
        let movieDetailsVC = MovieDetails(nibName: "MovieDetails", bundle: nil )
        movieDetailsVC.movName = movieData[indexPath.row].name
        movieDetailsVC.movImage = imageCache[indexPath.row]
        movieDetailsVC.movIMDB = movieData[indexPath.row].imdb
        navigationController?.pushViewController(movieDetailsVC, animated: true)
        
}

    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "mycell", for: indexPath)
        let imageView:UIImageView = UIImageView()
        imageView.frame = CGRect(x: 0, y: 0, width: 85, height: 100)
        imageView.contentMode = .scaleAspectFill
        imageView.image = imageCache[indexPath.row]
        cell.addSubview(imageView)
        let nameView:UITextView = UITextView()
        nameView.frame = CGRect(x: 0, y: 78, width: 85, height: 35)
        nameView.backgroundColor = .black
        nameView.textAlignment = .center
        nameView.textColor = .white
        nameView.alpha = 0.7
        nameView.text = nameArray[indexPath.row]
        nameView.isEditable = false
        cell.addSubview(nameView)
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
        self.activityIndicator.startAnimating()
        DispatchQueue.global(qos: .userInitiated).async {
            let s = self.searchBar.text ?? ""
            let ss = s.replacingOccurrences(of: " ", with: "+")
            let json1 = self.getJSON(url: "http://www.omdbapi.com/?s=\(ss)")
            let json2 = self.getJSON(url: "http://www.omdbapi.com/?s=\(ss)&page=2")
            
            
            let resultList1 = json1["Search"].arrayValue
            let resultList2 = json2["Search"].arrayValue

            for result in resultList1 {
                let name = result["Title"].stringValue
                let url = result["Poster"].stringValue
                let imdb = result["imdbID"].stringValue
                let year = result["Year"].stringValue

                self.movieData.append(Movie(name:name, url:url, imdb:imdb, year:year))
                self.nameArray.append(name)
            }
            
            for result in resultList2 {
                let name = result["Title"].stringValue
                let url = result["Poster"].stringValue
                let imdb = result["imdbID"].stringValue
                let year = result["Year"].stringValue

                self.movieData.append(Movie(name:name, url:url, imdb:imdb, year:year))
                self.nameArray.append(name)
            }
            DispatchQueue.main.async {
                if (resultList1 == []) {
                    self.errorLabel.text = "Error: No Movies Found"
                    self.errorIcon.isHidden = false
                    print("Error")
                }
                else {
                    self.errorLabel.text = nil
                    self.errorIcon.isHidden = true
                }
                self.cacheImages()
                self.theCollectionView.reloadData()
                self.activityIndicator.stopAnimating()
                self.searchBar.text = nil
            }

        }

        

    }
    
    //cache images
    private func cacheImages() {
        for movie in movieData {
            let url = URL(string: movie.url)
            let data = try? Data(contentsOf: url!)
            var image = UIImage(named: "Default")
            if (data != nil) {
                image = UIImage(data: data!)
            }
            imageCache.append(image!)
        }
    }
    
    // searchbar 
    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        searchActive = true

    }
   func searchBarTextDidEndEditing(_ searchBar: UISearchBar) {
        searchActive = false

    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        searchActive = false
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        movieData.removeAll()
        imageCache.removeAll()
        nameArray.removeAll()
        self.errorLabel.text = nil
        self.errorIcon.isHidden = true
        theCollectionView.reloadData()
        fetchDataForCollectionView()
        searchActive = false
        self.searchBar.endEditing(true)

    }

    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        searchActive = true

    }
    override func viewDidLoad() {
        super.viewDidLoad()
        theCollectionView.dataSource = self
        theCollectionView.delegate = self
        self.activityIndicator.hidesWhenStopped = true
        self.searchBar.delegate = self
        self.title = "Movies"
        self.errorIcon.image = UIImage(named: "sad")
        self.errorIcon.isHidden = true

    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}


