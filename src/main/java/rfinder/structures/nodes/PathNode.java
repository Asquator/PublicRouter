package rfinder.structures.nodes;

import rfinder.dao.FootpathDAO;
import rfinder.query.result.TerminalPathElement;
import rfinder.structures.common.Location;
import rfinder.structures.graph.GraphNode;

import java.util.Objects;
import java.util.Set;

public sealed class PathNode implements GraphNode<Integer> permits StopNode, VertexNode {
    private Location location;
    private final int id;

    public PathNode(Location location, int id){
        this.location = location;
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isStop(){
        return this instanceof StopNode;
    }

    public TerminalPathElement toElement(){
        return new TerminalPathElement(location);
    }

    @Override
    public Integer id() {
        return id;
    }

    public Set<StopNode> getFootpathsWith(FootpathDAO dao, double radius){
        return dao.getFootPaths(this.location, radius);
    }

    @Override
    public String toString() {
        return "PathNode{" +
                "location=" + location +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        return id == pathNode.id;
    }
}
