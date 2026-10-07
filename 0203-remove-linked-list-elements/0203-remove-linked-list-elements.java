/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode h, int v) {
        ListNode a=new ListNode(0,h);
        ListNode d=a;
        while(d!=null){
            while(d.next !=null && d.next.val == v){
                d.next =d.next.next;
            }
            d=d.next;
        }
        return a.next;
    }
}