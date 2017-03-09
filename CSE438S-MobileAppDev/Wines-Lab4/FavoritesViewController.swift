//
//  FavoritesViewController.swift
//  Wines-Lab4
//
//  Created by Nikki Wines on 2/28/17.
//  Copyright © 2017 Nikki Wines. All rights reserved.
//

import UIKit

class FavoritesViewController: UIViewController , UITableViewDataSource, UITableViewDelegate {
    
    var favMovies: [String] = []
    var tableView: UITableView!
    var count = 0
    

    
    //setup table view
    private func setUpTableView() {
        tableView = UITableView(frame: view.frame.offsetBy(dx: 0, dy: 10)) // table view in upper left
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        view.addSubview(tableView)
    }

    private func loadTable(){
        let userDefaults = UserDefaults.standard
        favMovies = userDefaults.array(forKey: "Favorites") as? [String] ?? [String]()
        count = favMovies.count;
        self.tableView.reloadData()

        // have array and use count of array - use same data for num of
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = UITableViewCell(style: .subtitle, reuseIdentifier: "cell")
        cell.detailTextLabel?.text = favMovies[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return count
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    //deleting
     func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
     func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) { // err in deleting last element.
        if (editingStyle == UITableViewCellEditingStyle.delete) {
            
            let userDefaults = UserDefaults.standard
            var favMovies = userDefaults.array(forKey: "Favorites") as? [String] ?? [String]()
            favMovies.remove(at: indexPath.row)

            count = favMovies.count
            userDefaults.set(favMovies, forKey: "Favorites")
        }
        loadTable()

    }

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Favorites"
        setUpTableView()
        loadTable()
        // Do any additional setup after loading the view, typically from a nib.

    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.tableView.reloadData()
        loadTable()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}
