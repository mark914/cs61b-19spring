import org.junit.Test;

public class UnionFind {

    // TODO - Add instance variables?
    int[] id;
    int size = 0;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        id = new int[n];
        for (int i = 1; i < n; i += 1) {
            id[i]  = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex)  {
        // TODO
        if (vertex > id.length || vertex < 0) {
            throw new IllegalArgumentException("Invalid index!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int root = find(v1);
        return -id[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        return v1Root == v2Root;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        int v1Size = sizeOf(v1Root);
        int v2Size = sizeOf(v2Root);
        if (v1Size <= v2Size) {
            id[v1Root] = v2Root;
            id[v2Root] = -(v1Size + v2Size);
        } else {
            id[v2Root] = v1Root;
            id[v1Root] = -(v1Size + v2Size);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int p = vertex;
        while (parent(p) > 0) {
            p = parent(p);
        }
        return p;
    }





}
