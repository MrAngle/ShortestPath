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
    private static long shortesTime = 999999999;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);

        dataLoaderByFileService = new DataLoaderByFileServiceImpl();

    }


    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    public void init() {
        long startTime = System.currentTimeMillis();

        Set<Country> countries = dataLoaderByFileService.loadData();
        System.out.println(countries.size());
        MyGraph graph = new MyGraph();
        Country NAM = countries.stream().filter(x -> x.getCountryCode().equals("NAM")).findFirst().get();
        for (Country country: countries) {
            graph.addNode(country);
        }
        MyGraph.calculateShortestPathFromSource(graph, NAM);

        long endTime = System.currentTimeMillis();
        if(shortesTime > (endTime-startTime) ) {
            shortesTime = (endTime-startTime);
        }

        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println("Shortests: " + shortesTime);
    }
}
