public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<P, M>(pos, move, node);
                List<M> result = search(child);
                if (result != null)
                    return result;
            }
        }
        return null;
    }

        @Immutable
        static class Node<P, M> {
            final P pos;
            final M move;
            final Node<P, M> prev;

            Node(P pos, M move, Node<P, M> prev) {

            }
            List<M> asMoveList() {
                List<M> solution = new LinkedList<M>();
                for (Node<P, M> n = this; n.move != null; n = n.prev)
                    solution.add(0, n.move);
                return solution;
            }
        }
}
