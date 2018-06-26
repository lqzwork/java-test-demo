package com.liqz.test.nio;

// $Id$

import java.nio.*;

public class UseFloatBuffer
{
  static public void main( String args[] ) throws Exception {
    FloatBuffer buffer = FloatBuffer.allocate( 10 );

    for (int i=0; i<buffer.capacity(); ++i) {
      buffer.put( i );
    }

    buffer.flip();

    while (buffer.hasRemaining()) {
      float f = buffer.get();
      System.out.println( f );
    }
  }
}
