//
//  ViewController.m
//  TestApp
//
//  Created by Riemschneider Jens on 09/12/15.
//  Copyright © 2015 Riemschneider Jens. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)onSendEventClicked:(id)sender {
    [self.reactView sendEvent];
}

@end
