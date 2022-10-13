public class RubiksKubus {
    double[] myArray = { 0, 0, 0 };
    Kubusje[] littleCube = new Kubusje[27];

    public RubiksKubus() {
        int a = -1;

        for (int i = 0; i < 3; i++) {
            myArray[0] = i;
            for (int i2 = 0; i2 < 3; i2++) {
                myArray[1] = i2;
                for (int i3 = 0; i3 < 3; i3++) {
                    a++;
                    myArray[2] = i3;
                    Kubusje myCube = new Kubusje(myArray.clone());
                    littleCube[a] = myCube;
                }
            }
        }
    }
}
