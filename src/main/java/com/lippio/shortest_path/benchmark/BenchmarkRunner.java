package com.lippio.shortest_path.benchmark;

import com.lippio.shortest_path.dijkstra.MyGraph;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.DataLoaderByFileServiceImpl;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {


    private static DataLoaderByFileServiceImpl dataLoaderByFileService = new DataLoaderByFileServiceImpl();
    private static long minTime = 999999999;
    private static long maxTime = -1;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);

        dataLoaderByFileService = new DataLoaderByFileServiceImpl();

    }

    void longExample() {
        Set<Country> countries = dataLoaderByFileService.loadData();
        MyGraph graph = new MyGraph();
        Country NAM = countries.stream().filter(x -> x.getCountryCode().equals("NAM")).findFirst().get();
        Country KOR = countries.stream().filter(x -> x.getCountryCode().equals("KOR")).findFirst().get();
        MyGraph.calculateShortestPathFromSource(graph, NAM, KOR);
    }

    void shortExample() {
        Set<Country> countries = dataLoaderByFileService.loadData();
        MyGraph graph = new MyGraph();
        Country CZE = countries.stream().filter(x -> x.getCountryCode().equals("CZE")).findFirst().get();
        Country ITA = countries.stream().filter(x -> x.getCountryCode().equals("ITA")).findFirst().get();
        MyGraph.calculateShortestPathFromSource(graph, CZE, ITA);
    }



    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(time = 5, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(time = 5, timeUnit = TimeUnit.SECONDS, iterations = 2)
    public void init() {
        long startTime = System.currentTimeMillis();

        shortExample();

        long endTime = System.currentTimeMillis();
        if(minTime > (endTime-startTime) ) {
            minTime = (endTime-startTime);
        }
        if(maxTime < (endTime-startTime) ) {
            maxTime = (endTime-startTime);
        }


        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println("MIN: " + minTime);
        System.out.println("MAX: " + maxTime);
    }
}
