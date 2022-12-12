public class FloodFill {
    /*
    An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

    You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].

    To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.

    Return the modified image after performing the flood fill.
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int newColor = color;
        color = image[sr][sc]; // first array is rows, second array is columns.
        // Must check that two two colors aren't the same or. you may get stack overflow.
        if(color!=newColor)fill(sr, sc, color, newColor,image);
        return image;
    }
    private static void fill(int sr, int sc, int color, int newColor, int[][] image){
        if(image[sr][sc] == color){
            image[sr][sc] = newColor;
            if(sr>=1) fill(sr-1,sc,color,newColor,image);
            if(sc>=1) fill(sr,sc-1,color,newColor,image);
            if(sr+1<image.length) fill(sr+1,sc,color,newColor,image);
            if(sc+1<image[0].length) fill(sr,sc+1,color,newColor,image);
        }
    }

    public static void main(String[] args){
        int[][] example1 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        floodFill(example1, 1, 1, 2);
    }
    /*
    Something I had to be reminded about multi-dimensional arrays in Java:

     How to access the length of each dimension.
        This is for a 3 dimensional array.

         int x[][][]=new int[5][8][10];
                System.out.println(x.length+" "+x[1].length+" "+x[0][1].length);
        OUTPUT : 5 8 10

     And that each dimension can be of a different length, unlike C/C++ where they must be the same.
     */

    /* EXAMPLE 1
    Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
    Output: [[2,2,2],[2,2,0],[2,0,1]]
    Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel),
    all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are
     colored with the new color.
    Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
     */

}
