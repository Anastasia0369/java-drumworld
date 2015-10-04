import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by donhan on 10/3/15.
 */
public class MedianOfIntegerStream {
    public Queue<Integer> maxHeap;
    public Queue<Integer> minHeap;
    public Integer numOfElements;

    private class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public MedianOfIntegerStream() {
        this.minHeap = new PriorityQueue<Integer>();
        Comparator<Integer> cmp = Collections.reverseOrder(null);// we can write our own comparator as above.
        this.maxHeap = new PriorityQueue<Integer>(10, cmp);
        this.numOfElements = 0;
    }

    public void addNumbers(Integer number){
        maxHeap.add(number);
        if(numOfElements % 2 == 0){
            if(minHeap.isEmpty()){
                numOfElements ++;
                return;
            }else if(minHeap.peek() < maxHeap.peek()){
                Integer minRoot = minHeap.poll();
                Integer maxRoot = maxHeap.poll();
                minHeap.add(maxRoot);
                maxHeap.add(minRoot);
            }
        }else{
            minHeap.add(maxHeap.poll());
        }
        numOfElements++;

    }

    public Double getMedian(){
        if(numOfElements % 2 == 0){
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }else{
            return new Double(maxHeap.peek());
        }
    }
    public static void main(String[] args) {
        MedianOfIntegerStream streamMedian = new MedianOfIntegerStream();

        streamMedian.addNumbers(1);
        System.out.println(streamMedian.getMedian()); // should be 1

        streamMedian.addNumbers(5);
        streamMedian.addNumbers(10);
        streamMedian.addNumbers(12);
        streamMedian.addNumbers(2);
        System.out.println(streamMedian.getMedian()); // should be 5

        streamMedian.addNumbers(3);
        streamMedian.addNumbers(8);
        streamMedian.addNumbers(9);
        System.out.println(streamMedian.getMedian()); // should be 6.5
    }
}
