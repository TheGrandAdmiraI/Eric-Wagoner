public class WorkerClass {

	public static void main(String[] args) {
		
		System.out.println("This is the intrusion detection system running.\n");
		
		TreeNode root = new TreeNode("departments.oakland.edu", null, null, null);
		
		TreeNode biologyComputer = new TreeNode("biology.oakland.edu", null, null, null);
		
		root.insertNewNode(root, biologyComputer);
		
		TreeNode accountingComputer = new TreeNode("accounting.oakland.edu", null, null, null);
		
		root.insertNewNode(root, accountingComputer);
		
		TreeNode computerScienceComputer = new TreeNode("computerscience.oakland.edu", null, null, null);
		
		root.insertNewNode(root, computerScienceComputer);
		
		TreeNode financeComputer = new TreeNode("finance.oakland.edu", null, null, null);
		
		root.insertNewNode(root, financeComputer);
		
		TreeNode educationComputer = new TreeNode("education.oakland.edu", null, null, null);
		
		root.insertNewNode(root, educationComputer);

		TreeNode industrialEngineeringComputer = new TreeNode("industrialengineering.oakland.edu", null, null, null);
		
		root.insertNewNode(root, industrialEngineeringComputer);
		System.out.println("\nin order\n");
		root.analyzeInOrder(root);
		System.out.println("\npre order\n");
		root.analyzeInPreOrder(root);
		System.out.println("\npost order\n");
		root.analyzeInPostOrder(root);
	}

}


