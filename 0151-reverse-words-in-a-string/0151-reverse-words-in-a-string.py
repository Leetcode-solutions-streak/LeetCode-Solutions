class Solution:
    def reverseWords(self, s: str) -> str:
        w=s.split()
        res =[]
        for i in range(len(w) -1,-1,-1):
            res.append(w[i])
            if i!=0:
                res.append(" ")
        return "".join(res)