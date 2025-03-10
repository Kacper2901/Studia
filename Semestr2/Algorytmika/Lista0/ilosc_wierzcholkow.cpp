#include <iostream>

struct Node {
    int data;
    Node* left;
    Node* right;

    Node(int value){
        data = value;
        left = nullptr;
        right = nullptr;
    }
};

Node* Insert(Node* root, int val){
    if(root == nullptr){
        return new Node(val);
    }
    
}