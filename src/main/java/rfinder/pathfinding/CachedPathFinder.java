package rfinder.pathfinding;

import rfinder.cache.FactoryCache;
import rfinder.structures.graph.GraphNode;
import rfinder.structures.graph.RoutableGraph;
import rfinder.structures.graph.RouteLink;

public class CachedPathFinder <T extends GraphNode<Integer>, L extends RouteLink<T>> implements GraphPathFinder<T, L> {

    protected final FactoryCache<T, ? extends SharedSourcePathContext<T>> cache;
    protected final RoutableGraph<T, L> graph;

    public CachedPathFinder(RoutableGraph<T, L> graph, FactoryCache<T, ? extends SharedSourcePathContext<T>> cache) {
        this.graph = graph;
        this.cache = cache;
    }

    @Override
    public GraphPath<T> findPath(T source, T destination) {
        return getContext(source).findPath(graph, destination);
    }


    private SharedSourcePathContext<T> getContext(T source) {
        SharedSourcePathContext<T> context = cache.get(source, graph);

        if(context == null) {
            throw new RuntimeException("Couldn't acquire for source: " + source);
        }

        return context;
    }
}