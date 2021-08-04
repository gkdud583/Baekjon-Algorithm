import java.util.*;




class baekjoon_1713{
    static void printPhotoFrame(HashMap<Integer,Integer> photoFrame){
        List<Integer> keys = new ArrayList<>(photoFrame.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            System.out.print(key+" ");
        }    
    }
    static void removePhoto(HashMap<Integer,Integer> photoFrame){
        List<Integer> keySet = new ArrayList<>(photoFrame.keySet());

        keySet.sort((o1, o2) -> photoFrame.get(o1) - photoFrame.get(o2));
        int key = keySet.get(0);
        photoFrame.remove(key);
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        int max = sc.nextInt();
        int n = sc.nextInt();
        HashMap<Integer,Integer> photoFrame = new LinkedHashMap<>();
        for(int i=0; i<n; i++){
            int student = sc.nextInt();
            if(!photoFrame.containsKey(student)){
                if(photoFrame.size() == max)
                    removePhoto(photoFrame);
                photoFrame.put(student,1);
            }
            else{
                photoFrame.replace(student, photoFrame.get(student)+1);
            }

            
        }
        printPhotoFrame(photoFrame);
    }

}
