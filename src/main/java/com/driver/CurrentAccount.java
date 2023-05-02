package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,0);
        this.tradeLicenseId=tradeLicenseId;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
    if(!isValid(tradeLicenseId)){
        String  rearrange=rearangeString(tradeLicenseId);
        if (rearrange==""){
            throw new Exception("Valid License can not be generated");

        }else
            this.tradeLicenseId=rearrange;
    }
    }
    public String rearangeString(String s){
        int n=s.length();
        char[] count=new char[26];
        for(char ch:s.toCharArray()){
            count[(int)ch-(int)'A']++;
        }
        char ans=getCharCount(count);
        int max_count=count[(int)ans-(int)'A'];
        if(n%2==0){
            if(max_count>(n/2)+1) return "";
        }else{
            if(max_count>(n/2)+2) return "";
        }
        char[] res=new char[n];
        int k;
        for(k=0;k<n;k=k+2){
            if(count[max_count]>0){
                res[k]=ans;
                count[ans]--;

            }else break;
        }
        for(int i=0;i<26;i++){
            char c=(char)('A'+i);
            while (count[i]>0){
                if(k>=26){
                    k=1;
                }
                res[k]=c;
                k=k+2;
                count[i]--;
            }
        }
        String str=String.valueOf(res);
        return str;
    }
    public char getCharCount(char[] count){
        int max=0;
        char ch=0;
        for(int i=0;i<26;i++){
            if(count[i]>max){
                max=count[i];
                ch=(char)((int)'A'+i);
            }
        }return ch;
    }
    public boolean isValid(String tradeLicenseId){
        for (int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
                return false;
            }else
                return true;
        }
        return true;
    }

}
