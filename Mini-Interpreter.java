import java.io.*;
import java.util.*;

/*This mini-interpreter takes an input file with lines of commands like push, addition, negation, etc.
  It can therefore add integers, bind a value to a string, and more.*/
public class hw3{
	public static int lineType(String lineToCheck){
		String firstword = null;
		if(lineToCheck.contains(" ")){
			firstword = lineToCheck.substring(0, lineToCheck.indexOf(" "));
		}else{
			firstword = lineToCheck;
		}
		switch (firstword){
		case("push"):
			return 1;
		case("pop"):
			return 2;
		case(":true:"):
			return 3;
		case(":false:"):
			return 3;
		case(":error:"):
			return 4;
		case("add"):
			return 5;
		case("sub"):
			return 6;
		case("mul"):
			return 7;
		case("div"):
			return 8;
		case("rem"):
			return 9;
		case("neg"):
			return 10;
		case("swap"):
			return 11;
		case("quit"):
			return 12;
		case("and"):
			return 13;
		case("or"):
			return 14;
		case("not"):
			return 15;
		case("equal"):
			return 16;
		case("lessThan"):
			return 17;
		case("bind"):
			return 18;
		case("if"):
			return 19;
		case("let"):
			return 20;
		case("end"):
			return 21;
		}
		return 0;
	}
	public static void hw3(String input,String output){
		try{
			File inputFile = new File(input);
			File outputFile = new File(output);
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			PrintWriter printWriter = new PrintWriter(new FileWriter(outputFile));
			String line;
			ArrayList<Stack> stackArray = new ArrayList();
			ArrayList<HashMap> mapArray = new ArrayList();
			stackArray.add(new Stack());
			mapArray.add(new HashMap());
			int currentStackNum = 0;
			int currentMapNum = 0;
			Object x;
			Object y;
			while((line = bufferedReader.readLine()) != null){
				Stack outputStack = stackArray.get(currentStackNum);
				HashMap bindMap = mapArray.get(currentMapNum);
				int linecommand = lineType(line);
				switch(linecommand){
				case(1): //push
					String number = line.substring(line.indexOf(" ") + 1);
					if(number.equals("-0")){
						outputStack.push(0);
					}
					if(number.indexOf("\"")==0){
						String a_word = number.substring(0, number.length()-1);
						outputStack.push(a_word);
					}else if(number.indexOf(".") == -1){
						if (Character.isDigit(number.charAt(0))){
							if(number.matches(".*[a-zA-Z]+.*")){
								outputStack.push(":error:");
							}else{
								int trueNumber = Integer.parseInt(number);
								outputStack.push(trueNumber);
							}
						}else if (Character.isAlphabetic(number.charAt(0))){
							outputStack.push(number);
						}
					}
					else{
						outputStack.push(":error:");
					}
					break;
				case(2):
					if(outputStack.isEmpty()){
						outputStack.push(":error:");
					}else{
						outputStack.pop();
					}
					break;
				case(3):
					outputStack.push(line);
					break;
				case(4):
					outputStack.push(line);
					break;
				case(5): //add
					if (outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						x = outputStack.peek();
						outputStack.pop();
						y = outputStack.peek();
						outputStack.pop();
						Object xx;
						Object yy;
						if(x instanceof Integer){
							xx = (int)x;
						}else{
							xx = bindMap.get(x);
						}
						if(y instanceof Integer){
							yy = (int)y;
						}else{
							yy = bindMap.get(y);
						}
						if(xx==null || yy==null){
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}else if(xx instanceof Integer && yy instanceof Integer){
							int sum = (int)xx + (int)yy;
							outputStack.push(sum);
						}else{
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}
					}
					break;
				case(6): //subtract
					if (outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						x = outputStack.peek();
						outputStack.pop();
						y = outputStack.peek();
						outputStack.pop();
						Object xx;
						Object yy;
						if(x instanceof Integer){
							xx = (int)x;
						}else{
							xx = bindMap.get(x);
						}
						if(y instanceof Integer){
							yy = (int)y;
						}else{
							yy = bindMap.get(y);
						}
						if(xx==null || yy==null){
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}else if(xx instanceof Integer && yy instanceof Integer){
							int difference = (int)yy - (int)xx;
							outputStack.push(difference);
						}else{
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}
					}
					break;
				case(7): //multiply
					if (outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						x = outputStack.peek();
						outputStack.pop();
						y = outputStack.peek();
						outputStack.pop();
						Object xx;
						Object yy;
						if(x instanceof Integer){
							xx = (int)x;
						}else{
							xx = bindMap.get(x);
						}
						if(y instanceof Integer){
							yy = (int)y;
						}else{
							yy = bindMap.get(y);
						}
						if(xx==null || yy==null){
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}else if(xx instanceof Integer && yy instanceof Integer){
							int product = (int)xx * (int)yy;
							outputStack.push(product);
						}else{
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}
					}
					break;
				case(8)://divide
					if (outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						x = outputStack.peek();
						outputStack.pop();
						y = outputStack.peek();
						outputStack.pop();
						Object xx;
						Object yy;
						if(x instanceof Integer){
							xx = (int)x;
						}else{
							xx = bindMap.get(x);
						}
						if(y instanceof Integer){
							yy = (int)y;
						}else{
							yy = bindMap.get(y);
						}
						if(xx==null || yy==null){
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}else if(xx instanceof Integer && yy instanceof Integer && (int)xx !=0){
							int quotient = (int)yy / (int)xx;
							outputStack.push(quotient);
						}else{
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}
					}
					break;
				case(9)://rem
					if (outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						x = outputStack.peek();
						outputStack.pop();
						y = outputStack.peek();
						outputStack.pop();
						Object xx;
						Object yy;
						if(x instanceof Integer){
							xx = (int)x;
						}else{
							xx = bindMap.get(x);
						}
						if(y instanceof Integer){
							yy = (int)y;
						}else{
							yy = bindMap.get(y);
						}
						if(xx==null || yy==null){
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}else if(xx instanceof Integer && yy instanceof Integer && (int)xx !=0){
							int remainder = (int)yy % (int)xx;
							outputStack.push(remainder);
						}else{
							outputStack.push(y);
							outputStack.push(x);
							outputStack.push(":error:");
						}
					}
					break;
				case(10)://neg
					if(outputStack.isEmpty()){
						outputStack.push(":error:");
					}else{
						Object topOfStack = outputStack.peek();
						outputStack.pop();
						if(topOfStack instanceof Integer){
							int negation = (int)topOfStack * -1;
							outputStack.push(negation);
						}else if(!(topOfStack instanceof Integer)){
							Object z = bindMap.get(topOfStack);
							if(z != null && z instanceof Integer){
								int negation = (int)z * -1;
								outputStack.push(negation);
							}else{
								outputStack.push(topOfStack);
								outputStack.push(":error:");
							}
					}
					else{
						outputStack.push(":error:");
						}
					}
					break;
				case(11)://swap
					if(outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						Object a = outputStack.peek();
						outputStack.pop();
						Object b = outputStack.peek();
						outputStack.pop();
						outputStack.push(a);
						outputStack.push(b);
					}
					break;
				case(12): //quit
					while(!outputStack.isEmpty()){
						String thingtoPrint = outputStack.peek().toString();
						if(thingtoPrint.indexOf("\"")==0){
							thingtoPrint = thingtoPrint.substring(1);
						}
						printWriter.println(thingtoPrint);
						outputStack.pop();
					}
					break;
				case(13): //and
					if(outputStack.isEmpty() || outputStack.size() == 1){
						outputStack.push(":error:");
					}else{
						Object aa = outputStack.peek();
						outputStack.pop();
						Object bb = outputStack.peek();
						outputStack.pop();
						if((aa.equals(":true:") || aa.equals(":false:")) && (bb.equals(":true:") || 
								bb.equals(":false:"))){
							if(aa.equals(":true:") && bb.equals(":true:")){
								outputStack.push(":true:");
							}else{
								outputStack.push(":false:");
							}
						}else{
							if(bindMap.get(aa)!=null && bindMap.get(bb)!=null){
								if((bindMap.get(aa).equals(":true:") || bindMap.get(aa).equals(":false:")) 
										&& (bindMap.get(bb).equals(":true:") || bindMap.get(bb).equals(":false:"))){
										String aaa = (String)bindMap.get(aa);
										String bbb = (String)bindMap.get(bb);
										if(aaa.equals(":true:") && bbb.equals(":true:")){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
									outputStack.push(bb);
									outputStack.push(aa);
									outputStack.push(":error:");
									}
							}else if(bindMap.get(aa)!=null || bindMap.get(bb)!=null){
								if(bindMap.get(aa)!=null){
									Object aaa = bindMap.get(aa);
									if((aaa.equals(":true:") || aaa.equals(":false:")) && (bb.equals(":true:") || bb.equals(":false:"))){
										if(aaa.equals(":true:") && bb.equals(":true:")){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(bb);
										outputStack.push(aa);
										outputStack.push(":error:");
									}
								}else{
									Object bbb = bindMap.get(bb);
									if((aa.equals(":true:") || aa.equals(":false:")) && (bbb.equals(":true:") || bbb.equals(":false:"))){
										if(aa.equals(":true:") && bbb.equals(":true:")){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(bb);
										outputStack.push(aa);
										outputStack.push(":error:");
									}
								}
							}else{
								outputStack.push(bb);
								outputStack.push(aa);
								outputStack.push(":error:");
							}
						}
					}
					break;
				case(14): //or
					if(outputStack.isEmpty() || outputStack.size() == 1){
						outputStack.push(":error:");
					}else{
						Object aa = outputStack.peek();
						outputStack.pop();
						Object bb = outputStack.peek();
						outputStack.pop();
						if((aa.equals(":true:") || aa.equals(":false:")) && (bb.equals(":true:") || bb.equals(":false:"))){
							if(aa.equals(":false:") && bb.equals(":false:")){
								outputStack.push(":false:");
							}else{
								outputStack.push(":true:");
							}
						}else{
							if(bindMap.get(aa)!=null && bindMap.get(bb)!=null){
								if((bindMap.get(aa).equals(":true:") || bindMap.get(aa).equals(":false:")) 
									&& (bindMap.get(bb).equals(":true:") || bindMap.get(bb).equals(":false:"))){
										String aaa = (String) bindMap.get(aa);
										String bbb = (String) bindMap.get(bb);
										if(aaa.equals(":false:") && bbb.equals(":false:")){
											outputStack.push(":false:");
										}else{
											outputStack.push(":true:");
										}
								}else{
									outputStack.push(bb);
									outputStack.push(aa);
									outputStack.push(":error:");
									}
							}else if(bindMap.get(aa)!=null || bindMap.get(bb)!=null){
								if(bindMap.get(aa)!=null){
									Object aaa = bindMap.get(aa);
									if((aaa.equals(":true:") || aaa.equals(":false:")) && (bb.equals(":true:") || bb.equals(":false:"))){
										if(aaa.equals(":false:") && bb.equals(":false:")){
											outputStack.push(":false:");
										}else{
											outputStack.push(":true:");
										}
									}else{
										outputStack.push(bb);
										outputStack.push(aa);
										outputStack.push(":error:");
									}
								}else{
									Object bbb = bindMap.get(bb);
									if((aa.equals(":true:") || aa.equals(":false:")) && (bbb.equals(":true:") || bbb.equals(":false:"))){
										if(aa.equals(":false:") && bbb.equals(":false:")){
											outputStack.push(":false:");
										}else{
											outputStack.push(":true:");
										}
									}else{
										outputStack.push(bb);
										outputStack.push(aa);
										outputStack.push(":error:");
									}
								}
							}else{
								outputStack.push(bb);
								outputStack.push(aa);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(15): //not
					if(outputStack.isEmpty()){
						outputStack.push(":error:");
					}else{
						Object abc = outputStack.peek();
						outputStack.pop();
						if(abc.equals(":true:")){
							outputStack.push(":false:");
						}else if (abc.equals(":false:")){
							outputStack.push(":true:");
						}else{
							if(bindMap.get(abc)!=null){
								if (bindMap.get(abc).equals(":true:")){
									outputStack.push(":false:");
								}else if(bindMap.get(abc).equals(":false:")){
									outputStack.push(":true:");
								}else{
									outputStack.push(abc);
									outputStack.push(":error:");
								}
							}else{
								outputStack.push(abc);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(16): //equal
					if(outputStack.isEmpty() || outputStack.size() == 1){
						outputStack.push(":error:");
					}else{
						Object topNumber = outputStack.peek();
						outputStack.pop();
						Object secondNum = outputStack.peek();
						outputStack.pop();
						if(topNumber instanceof Integer && secondNum instanceof Integer){
							if(topNumber == secondNum){
								outputStack.push(":true:");
							}else{
								outputStack.push(":false:");
							}
						}else{
							if(bindMap.get(topNumber)!=null && bindMap.get(secondNum)!=null){
								Object XtopNum = bindMap.get(topNumber);
								Object YsecNum = bindMap.get(secondNum);
								if(XtopNum instanceof Integer && YsecNum instanceof Integer){
									if(XtopNum == YsecNum){
										outputStack.push(":true:");
									}else{
										outputStack.push(":false:");
									}
								}else{
									outputStack.push(secondNum);
									outputStack.push(topNumber);
									outputStack.push(":error:");
								}
							}else if(bindMap.get(topNumber)!=null || bindMap.get(secondNum)!=null){
								if(bindMap.get(topNumber)!=null){
									Object XXtopN = bindMap.get(topNumber);
									if(XXtopN instanceof Integer && secondNum instanceof Integer){
										if(XXtopN == secondNum){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(secondNum);
										outputStack.push(topNumber);
										outputStack.push(":error:");
									}
								}else{
									Object YYsecN = bindMap.get(secondNum);
									if(topNumber instanceof Integer && YYsecN instanceof Integer){
										if(topNumber == YYsecN){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(secondNum);
										outputStack.push(topNumber);
										outputStack.push(":error:");
									}
								}
							}else{
								outputStack.push(secondNum);
								outputStack.push(topNumber);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(17): //lessThan
					if(outputStack.isEmpty() || outputStack.size()==1){
						outputStack.push(":error:");
					}else{
						Object greater = outputStack.peek();
						outputStack.pop();
						Object less = outputStack.peek();
						outputStack.pop();
						if(greater instanceof Integer && less instanceof Integer){
							if((int)greater > (int)less){
								outputStack.push(":true:");
							}else{
								outputStack.push(":false:");
							}
						}else{
							if(bindMap.get(greater)!=null && bindMap.get(less)!=null){
								Object Xgreater = bindMap.get(greater);
								Object Yless = bindMap.get(less);
								if(Xgreater instanceof Integer && Yless instanceof Integer){
									if((int)Xgreater > (int)Yless){
										outputStack.push(":true:");
									}else{
										outputStack.push(":false:");
									}
								}else{
									outputStack.push(less);
									outputStack.push(greater);
									outputStack.push(":error:");
								}
							}else if(bindMap.get(greater)!=null || bindMap.get(less)!=null){
								if(bindMap.get(greater)!=null){
									Object XXgreater = bindMap.get(greater);
									if(XXgreater instanceof Integer && less instanceof Integer){
										if((int)XXgreater > (int)less){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(less);
										outputStack.push(greater);
										outputStack.push(":error:");
									}
								}else{
									Object YYless = bindMap.get(less);
									if(greater instanceof Integer && YYless instanceof Integer){
										if((int)greater > (int)YYless){
											outputStack.push(":true:");
										}else{
											outputStack.push(":false:");
										}
									}else{
										outputStack.push(less);
										outputStack.push(greater);
										outputStack.push(":error:");
									}
								}
							}else{
								outputStack.push(less);
								outputStack.push(greater);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(18): //bind
					if(outputStack.isEmpty() || outputStack.size() == 1){
						outputStack.push(":error:");
					}else{
						Object value = outputStack.peek();
						outputStack.pop();
						Object name = outputStack.peek();
						outputStack.pop();
						if(name instanceof Integer){
							outputStack.push(name);
							outputStack.push(value);
							outputStack.push(":error:");
						}else if(name.toString().indexOf("\"")!= -1){
							outputStack.push(name);
							outputStack.push(value);
							outputStack.push(":error:");
						}else{
							if(value instanceof Integer){
								bindMap.put(name, value);
								outputStack.push(":unit:");
							}else if(value instanceof String){
								if(!value.equals(":error:")){
									if(value.toString().indexOf("\"")==0){
										String stringtoBind = ((String) value).substring(1);
										bindMap.put(name, stringtoBind);
									}
									if(!bindMap.containsKey(value)){
										bindMap.put(name, value);
										outputStack.push(":unit:");
									}else{
										bindMap.put(name, bindMap.get(value));
										outputStack.push(":unit:");
									}
								}
							}else{
								outputStack.push(name);
								outputStack.push(value);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(19): //if
					if(outputStack.empty() || outputStack.size()>3){
						outputStack.push(":error:");
					}else{
						Object a1 = outputStack.peek();
						outputStack.pop();
						Object a2 = outputStack.peek();
						outputStack.pop();
						Object boo = outputStack.peek();
						outputStack.pop();
						if(!(boo instanceof String)){
							outputStack.push(boo);
							outputStack.push(a2);
							outputStack.push(a1);
							outputStack.push(":error:");
						}else{
							if(boo.equals(":true:")){
								outputStack.push(a1);
							}else if(boo.equals(":false:")){
								outputStack.push(a2);
							}else if(bindMap.get(boo)!=null){
								if(bindMap.get(boo) instanceof String){
									String bindedBoo = (String)bindMap.get(boo);
									if(bindedBoo.equals(":true:")){
										outputStack.push(a1);
									}else if (bindedBoo.equals(":false:")){
										outputStack.push(a2);
									}else{
										outputStack.push(boo);
										outputStack.push(a2);
										outputStack.push(a1);
										outputStack.push(":error:");
									}
								}else{
									outputStack.push(boo);
									outputStack.push(a2);
									outputStack.push(a1);
									outputStack.push(":error:");
								}
							}else{
								outputStack.push(boo);
								outputStack.push(a2);
								outputStack.push(a1);
								outputStack.push(":error:");
							}
						}
					}
				break;
				case(20): //let
					stackArray.add(new Stack());
					currentStackNum = currentStackNum + 1;
					HashMap cloneMap = new HashMap();
					cloneMap = (HashMap)bindMap.clone();
					mapArray.add(cloneMap);
					currentMapNum = currentMapNum + 1;
					break;
				case(21): //end
					if(outputStack.isEmpty()){
						outputStack.push(":error:");
					}
					Object lastFrame = outputStack.peek();
					currentStackNum = currentStackNum - 1;
					Stack prevStack = stackArray.get(currentStackNum);
					prevStack.add(lastFrame);
					stackArray.remove(stackArray.size()-1);
					currentMapNum = currentMapNum - 1;
					mapArray.remove(mapArray.size() - 1);
					break;
				}
			}
			bufferedReader.close();
			printWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}