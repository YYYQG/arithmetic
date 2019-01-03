import java.util.Stack;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 */
public class Maximum_Depth_Of_Binary_Tree {

    //递归
    public int maxDepth(TreeNode root) {
        return getMaxDepth(root);
    }

    public int getMaxDepth(TreeNode root){
        return root==null?0:Math.max(getMaxDepth(root.right),getMaxDepth(root.left))+1;
    }

    //DFS
    public int maxDepth2(TreeNode root){

        if(root == null) {
            return 0;
        }
        Stack<TreeNode> node = new Stack<TreeNode>();
        Stack<Integer> value = new Stack<Integer>();
        int maxDep = 0;
        node.push(root);
        value.push(1);

        while(!node.empty()){
            TreeNode cur = node.pop();
            int dep = value.pop();
            maxDep = Math.max(maxDep,dep);
            if(cur.left!=null){
                node.push(cur.left);
                value.push(dep+1);
            }
            if(cur.right!=null){
                node.push(cur.right);
                value.push(dep+1);
            }
        }
        return maxDep;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}