package com.model;

public class Node {

	public Node left;
	public Node right;
	public String code;
	public String signature;

	
	public Node(Node left, Node right, String code, String signature) {
		super();
		this.left = left;
		this.right = right;
		this.code = code;
		this.signature = signature;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
