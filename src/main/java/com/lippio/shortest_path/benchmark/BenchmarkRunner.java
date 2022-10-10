package com.lippio.shortest_path.benchmark;

import com.lippio.shortest_path.dijkstra.CountryNode;
import com.lippio.shortest_path.dijkstra.DijkstraAlgorithm;
import com.lippio.shortest_path.pojo.Country;
import com.lippio.shortest_path.service.DataLoaderByFileServiceImpl;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;
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

    void shortExample () {
        Set<Country> countries = dataLoaderByFileService.getCountries();

        Set<CountryNode> countryNodes = CountryNode.toCountryNodes(countries);
        CountryNode fromNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase("CZE")).findFirst().orElseThrow();
        CountryNode toNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase("ITA")).findFirst().orElseThrow();

        List<CountryNode> shortestPath = DijkstraAlgorithm.calculateShortestPathFromSource(fromNode, toNode);
    }

    void longExample() {
        Set<Country> countries = dataLoaderByFileService.getCountries();

        Set<CountryNode> countryNodes = CountryNode.toCountryNodes(countries);
        CountryNode fromNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase("NAM")).findFirst().orElseThrow();
        CountryNode toNode =
                countryNodes.stream().filter(x -> x.getCountry().getCountryCode().equalsIgnoreCase("VNM")).findFirst().orElseThrow();

        List<CountryNode> shortestPath = DijkstraAlgorithm.calculateShortestPathFromSource(fromNode, toNode);
    }



    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(time = 5, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Warmup(time = 5, timeUnit = TimeUnit.SECONDS, iterations = 2)
    public void init() {
        longExample();
    }
}
