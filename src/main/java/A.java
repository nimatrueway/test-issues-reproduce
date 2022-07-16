interface Z {
  A method(A.AA arg);
}

interface A extends B<A.AA> {
  interface AA {
  }
}

interface B<X> {
}
