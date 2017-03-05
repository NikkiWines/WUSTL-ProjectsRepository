var myControllers = angular.module('myControllers', []);

myControllers.controller('CoffeesCtrl', ['$scope',
  function ($scope) {
    $scope.coffees =
       [
       {'id': 4,
       'brand': "Jimmy's Special Coffee",
       'name': 'Americano',
       'country': 'America',
       'countryURL': 'http://emojipedia-us.s3.amazonaws.com/cache/f1/c8/f1c8b000c625e5a81a6267c556a925b7.png',
       'imgURL': 'http://greekaus.com/wp-content/uploads/2016/03/coffee-shop.jpg',
       'reviews': [
       {'rating': 10,
       'comment': 'If I could rate it higher, I would!',
       'reviewer': 'Justin Case'
       },
       {'rating': 10,
       'comment': 'He does it again!',
       'reviewer': 'Eileen Dover'
       }
       ]
        },
        {'id': 3,
        'brand': "We Did Our Best",
        'name': 'Latte',
        'country': 'France',
        'countryURL': 'http://emojipedia-us.s3.amazonaws.com/cache/5e/ba/5ebafcfd2853a983dceb0627ee25eb54.png',
        'imgURL': 'https://www.drum.fit/wp-content/uploads/2016/03/cup-of-coffee.jpg',
        'reviews': [
        {'rating': 1,
        'comment': " a 'latte' yuckiness.",
        'reviewer': 'Tim Burr'
        },
        {'rating': 1,
        'comment': 'Is this even coffee?',
        'reviewer': 'Sue Flay'
        },
         {'rating': 1,
        'comment': 'The grossest thing I have ever had.',
        'reviewer': 'Myles Long'
        },
         {'rating': 5,
        'comment': 'I dont know what the fuss is about, i dont think its too bad!',
        'reviewer': 'Sara Bellum'
        }
        ]
        },
        {'id': 2,
        'brand': "Jimmy's Coffee",
        'name': 'Mocha',
        'country': 'America',
        'countryURL': 'http://emojipedia-us.s3.amazonaws.com/cache/f1/c8/f1c8b000c625e5a81a6267c556a925b7.png',
        'imgURL': 'http://www.medicalnewstoday.com/content/images/articles/298/298264/a-cup-of-coffee-with-a-spoon.jpg',
        'reviews': [
        {'rating': 10,
        'comment': 'What everyone should drink in the morning!',
        'reviewer': 'Earl Lee Riser'
        },
        {'rating': 10,
        'comment': 'A genius of everything coffee',
        'reviewer': 'Bob'
        }
        ]
        },
       {'id': 1,
       'brand': "Average Andy's Coffee",
       'name': 'Regular Coffee',
       'country': 'Denmark',
       'countryURL': 'http://emojipedia-us.s3.amazonaws.com/cache/40/cc/40ccf917d558d3587c3765b99a06fe02.png',
       'imgURL': 'http://www.livescience.com/images/i/000/052/689/original/coffee-cup-120516.jpg?interpolation=lanczos-none&downsize=*:1000',
       'reviews': [
               {'rating': 3,
               'comment': "Could've been crispier",
               'reviewer': "Chris P. Bacon"
               }
       ]
       }];

  }]);

myControllers.controller('ReviewsCtrl', ['$scope', '$routeParams',
  function($scope, $routeParams) {
    $scope.coffeeId = $routeParams.coffeeId;
    if ($scope.coffeeId == 1) {
      $scope.coffees =

         {'id': 1,
         'brand': "Average Andy's Coffee",
         'name': 'Regular Coffee',
         'country': 'Denmark',
         'imgURL': 'http://www.livescience.com/images/i/000/052/689/original/coffee-cup-120516.jpg?interpolation=lanczos-none&downsize=*:1000',
         'reviews': [
                 {'rating': '★★★☆☆☆☆☆☆☆',
                 'comment': "Could've been crispier",
                 'reviewer': "Chris P. Bacon"
                 }
         ]
       };

    }
    else if ($scope.coffeeId == 2) {
      $scope.coffees =

           {'id': 2,
           'brand': "Jimmy's Coffee",
           'name': 'Mocha',
           'country': 'America',
           'imgURL': 'http://www.medicalnewstoday.com/content/images/articles/298/298264/a-cup-of-coffee-with-a-spoon.jpg',
           'reviews': [
           {'rating': '★★★★★★★★★★',
           'comment': 'What everyone should drink in the morning!',
           'reviewer': 'Earl Lee Riser'
           },
           {'rating': '★★★★★★★★★★',
           'comment': 'A genius of everything coffee',
           'reviewer': 'Bob'
           }
           ]
         };

    }
    else if ($scope.coffeeId == 3) {
      $scope.coffees =

           {'id': 3,
           'brand': "We Did Our Best",
           'name': 'Latte',
           'country': 'France',
           'imgURL': 'https://www.drum.fit/wp-content/uploads/2016/03/cup-of-coffee.jpg',
           'reviews': [
           {'rating': '★☆☆☆☆☆☆☆☆☆',
           'comment': " a 'latte' yuckiness.",
           'reviewer': 'Tim Burr'
           },
           {'rating': '★☆☆☆☆☆☆☆☆☆',
           'comment': 'Is this even coffee?',
           'reviewer': 'Sue Flay'
           },
            {'rating': '★☆☆☆☆☆☆☆☆☆',
           'comment': 'The grossest thing I have ever had.',
           'reviewer': 'Myles Long'
           },
            {'rating': '★★★★★☆☆☆☆☆',
           'comment': 'I dont know what the fuss is about, i dont think its too bad!',
           'reviewer': 'Sara Bellum'
           }
           ]
         };
    }
    else {
      $scope.coffees =

          {'id': 4,
            'brand': "Jimmy's Special Coffee",
            'name': 'Americano',
            'country': 'America',
            'imgURL': 'http://greekaus.com/wp-content/uploads/2016/03/coffee-shop.jpg',
            'reviews': [
              {'rating': '★★★★★★★★★★',
                'comment': 'If I could rate it higher, I would!',
                'reviewer': 'Justin Case'
              },
              {'rating': '★★★★★★★★★★',
                'comment': 'He does it again!',
                'reviewer': 'Eileen Dover'
              }
            ]
          };

       }

  }]);
