package bld;


/**
 *  This class represents an artificial neuron with a given input size.
 *  It is able to both calculate an output with internal weights, these weights
 *  are able to be improved on by providing the correct answer after an output
 *  is calculated using the Neuron.train() function.
 *
 *  @author Josh
 */
public class Neuron
{
  /** Variable that holds the last input example.*/
  protected float[] lastInput = null;
  /** Size of input for this neuron, remains constant over lifetime. */
  protected final int numberOfInputConnections;
  /** An array representing the internal weights on input positions. */
  protected final double[] inputWeights;
  /** The output of the last input array. */
  protected float output;
  /** The rate of change for an incorrect response. */
  private static final double LEARNING_RATE = 0.1;

  /**
   *  Constructor that creates a basic neuron without activation function for
   *  linear regression.
   *
   *  @param The size of input this neuron takes.
   */
  public Neuron(int numberOfInputConnections)
  {
    this.numberOfInputConnections = numberOfInputConnections;
    this.inputWeights = new double[numberOfInputConnections];
    for(int inputNumber = 0; inputNumber < this.numberOfInputConnections;
        inputNumber++)
    {
      this.inputWeights[inputNumber] = 1.0;
    }
    output = Float.NaN;
  }

  /**
   *  Allows one to give an input to this neuron which will calculate an output.
   *  This output can be obtained using the Neuron.getOuput() getter method.
   *
   *  @param An input example of type double.
   */
  public float input(float... input) throws IllegalArgumentException
  {
    if(input.length != this.numberOfInputConnections)
      throw new IllegalArgumentException("Size mismatch between the neuron "
                                        +" size and input size.");
    this.output = 0;
    for(int inputNumber = 0; inputNumber < input.length; inputNumber++)
    {
      this.output += input[inputNumber] * this.inputWeights[inputNumber];
    }
    this.lastInput = input;
    System.out.println("" + this.output);
    return this.output;
  }

  /**
   *  Calling this function will attempt to improve the weights based on the
   *  last output and the provided correct output.
   *
   *  @param The correct output for the last input example.
   */
  public void train(float correctAnswer) throws IllegalArgumentException
  {
    if(this.lastInput == null)
      throw new IllegalArgumentException("No input provided.");
    for(int inputNumber = 0; inputNumber < this.numberOfInputConnections;
        inputNumber++)
    {
      this.inputWeights[inputNumber] += LEARNING_RATE
        * (correctAnswer - this.output) * this.lastInput[inputNumber];
    }
  }

  //GETTERS

  public float getOutput()
  {
    return this.output;
  }

  public double[] getWeights()
  {
    return this.inputWeights;
  }
}
