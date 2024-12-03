import java.util.*;

class ListNode {
    int data;
    ListNode next;

    ListNode(int x) {
        this.data = x;
        this.next = null;
    }
}

public class ReverseList {

    // Method to reverse the linked list
    static ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode tmp;
        while (head != null) {
            tmp = head.next;   // Store the next node
            head.next = prev;  // Reverse the current node's pointer
            prev = head;       // Move prev forward (to current node)
            head = tmp;        // Move head forward (to next node)
        }
        return prev;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ListNode head = null, tail = null;

        for (int i = 0; i < n; i++) {
            int x = s.nextInt();
            ListNode node = new ListNode(x);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        ListNode rev = reverseLinkedList(head);

        while (rev != null) {
            System.out.print(rev.data + " ");
            rev = rev.next;
        }
    }
}
