class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* curr = head;
        
        while (curr != nullptr && curr->next != nullptr) {
            if (curr->val == curr->next->val) {
                // Skip the duplicate node
                curr->next = curr->next->next;
            } else {
                // Move to the next unique node
                curr = curr->next;
            }
        }
        
        return head;
    }
};
