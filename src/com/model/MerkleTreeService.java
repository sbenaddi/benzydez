package com.model;

import java.util.ArrayList;
import java.util.List;

public class MerkleTreeService {
	
	public HashService hashService = new HashService();
	
	public Node generateRoot(List<Node> nodes) {
		while(nodes.size() > 1) {
			nodes = generate(nodes);
		}
		return nodes.get(0);
	}

	private List<Node> generate(List<Node> nodes) {
		List<Node> result = new ArrayList<>();
		int n = 0;
		Node lastNode = null;
		if (nodes.size() % 2 == 0) {
			n = nodes.size();
		} else {
			n = nodes.size()-1;
			lastNode = nodes.get(nodes.size()-1);
		}
		for(int i = 0 ; i < n ; i+=2) {
			Node node = new Node(nodes.get(i), nodes.get(i+1), hashService.encrypt(nodes.get(i).getCode()+"/"+ nodes.get(i+1).getCode()) , "signature");
			System.out.print(node.getCode());
			System.out.print("		");
			result.add(node);
		}
		
		if(lastNode != null) {
			result.add(lastNode);
		}
		System.out.println();
		return result;
	}

	public List<Node> generateListNodes(Integer nbr) {
		List<Node> result = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			Node node = new Node(null, null, "D_" + i, "signature_" + i);
			System.out.print(node.getCode());
			System.out.print("		");
			result.add(node);
		}
		System.out.println();
		return result;
	}

}
