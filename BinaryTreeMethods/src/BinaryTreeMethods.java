import components.binarytree.BinaryTree;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Connor Michetti.
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        int height = 0;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        if (t.size() > 0) {
            T root = t.disassemble(left, right);
            height++;
            int leftHeight = height(left);
            int rightHeight = height(right);
            if (leftHeight > rightHeight) {
                height += leftHeight;
            } else {
                height += rightHeight;
            }
            t.assemble(root, left, right);
        }
        return height;
    }

//    /**
//     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
//     * or false otherwise.
//     *
//     * @param <T>
//     *            the type of the {@code BinaryTree} node labels
//     * @param t
//     *            the {@code BinaryTree} to search
//     * @param x
//     *            the {@code T} to search for
//     * @return true if the given {@code T} is in the given {@code BinaryTree},
//     *         false otherwise
//     * @ensures isInTree = [true if x is in t, false otherwise]
//     */
//    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
//        assert t != null : "Violation of: t is not null";
//        assert x != null : "Violation of: x is not null";
//
//        boolean check = false;
//        for (T y : t) {
//            if (y.equals(x)) {
//                check = true;
//            }
//        }
//        return check;
//    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        boolean check = false;

        if (t.size() > 0) {
            T root = t.disassemble(left, right);
            if (root.equals(x)) {
                check = true;
            } else {
                boolean checkLeft = isInTree(left, x);
                boolean checkRight = isInTree(right, x);
                if ((checkLeft || checkRight) == true) {
                    check = true;
                }
            }
            t.assemble(root, left, right);

        }
        return check;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTree(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}
