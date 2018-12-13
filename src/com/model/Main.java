package com.model;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		MerkleTreeService merkleTreeService = new MerkleTreeService();
		List<Node> nodes = merkleTreeService.generateListNodes(5);
		Node root = merkleTreeService.generateRoot(nodes);
	}

}
