package rfinder.model.network.walking;

import rfinder.structures.common.Location;
import rfinder.structures.nodes.PathNode;
import rfinder.structures.nodes.VertexNode;

public record EdgeLinkage(PathNode closest, double kmClosest, VertexNode source, double kmSource, VertexNode target, double kmTarget) {

}