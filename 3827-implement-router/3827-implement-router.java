class Package {
    int source;
    int destination;
    int timestamp;

    Package(int s, int d, int t) {
        source=s;
        destination=d;
        timestamp=t;
    }
}
class Router {
    Queue<Package> queue = new LinkedList<>();
    Map<Integer, List<Package>> map = new HashMap<>();
    Map<Integer, Integer> firstNonExpInd = new HashMap<>();
    int size;
    public Router(int memoryLimit) {
        size=memoryLimit;
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        if(map.containsKey(destination)) {
            int[] ind=getInd(destination, timestamp, timestamp);
            List<Package> list = map.get(destination);
            if(ind[0]!=-1 && list.get(ind[0]).timestamp==timestamp) {
                for(int i=ind[0]; i<=ind[1]; i++) {
                    if(list.get(i).source==source)
                        return false;
                }
            }
        }
        
        if(queue.size()==size) {
            forwardPacket();
        }
        Package pc=new Package(source, destination, timestamp);
        queue.add(pc);
        map.computeIfAbsent(destination, k -> new ArrayList<>()).add(pc);
        firstNonExpInd.putIfAbsent(destination, 0);
        
        return true;
    }
    
    public int[] forwardPacket() {
        if(queue.isEmpty())
            return new int[0];
        Package p= queue.remove();
        int[] ind=getInd(p.destination, p.timestamp, p.timestamp);
        firstNonExpInd.put(p.destination, firstNonExpInd.get(p.destination)+1);
        return new int[]{p.source, p.destination,p.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        int[] ind=getInd(destination, startTime, endTime);
        if(ind[1]<0 || ind[0]<0)
            return 0;
        return ind[1]-ind[0]+1;
    }

    public int[] getInd(int destination, int startTime, int endTime) {
        int[] ind = new int[]{-1,-2};

        if(map.containsKey(destination)) {
            List<Package> list = map.get(destination);
            int n = list.size();
            int i=firstNonExpInd.get(destination), j=n-1;
            while(i<=j) { //floor 90 90 90 95 100 105 endTime largest ele <=endtime
                int m=i+(j-i)/2;
                int mTime=list.get(m).timestamp;
                if(mTime<=endTime) {
                    ind[1]=m;
                    i=m+1;
                }
                    
                else
                    j=m-1;
            }
            
            i=firstNonExpInd.get(destination);
            j=n-1;
            while(i<=j) { //ceil 90 90 90 95 100 105 120 smallest ele>=starttime
                int m=i+(j-i)/2;
                int mTime=list.get(m).timestamp;
                if(mTime<startTime)
                    i=m+1;
                else {
                    ind[0]=m;
                    j=m-1;
                }
            }
            
        }
        return ind;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */