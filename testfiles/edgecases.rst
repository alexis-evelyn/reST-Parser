#################
  Edge Case RST
#################

.. code-block:: Java

    public static void main(String[] args) {
        System.out.println("Hello World!!!");
    }

    public void test() {
        // Do Nothing!!!
    }

###########
Test Mashed
###########
#######
Test 2
#######

Test Mashed 2
~~~~~~~~~~~~~
Test Mashed 3
~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~
First 3, Then 2
~~~~~~~~~~~~~~~~
Am 2
~~~~~~~~~~~~~~~~

First 2, Then 3
~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~
Am 3
~~~~~~~~~~~~~~~~

1st 2-2-2
-------------------
2nd 2-2-2
-------------------
3rd 2-2-2
-------------------

-------------------
1st 3-3-3
-------------------
-------------------
2nd 3-3-3
-------------------
-------------------
3rd 3-3-3
-------------------

1st 2-3-2
-------------------
-------------------
2nd 2-3-2
-------------------
3rd 2-3-2
-------------------

.. IMPORTANT::
    https://docutils.sourceforge.io/docs/ref/rst/directives.html

    Cool!!!

Hello There!!!


    Why is this missing? Apparently the terminal is a dick as this isn't missing when I surround every token with quotes. The non-quoted also "skips" alternating a color like it should if there was a token there (which there is as you are reading it right now). This issue was solved by stripping extraneous newlines from the beginning and ends of tokens.

..

  Folks

    Test

..

    Test
    Cool

..

    Hi

  There