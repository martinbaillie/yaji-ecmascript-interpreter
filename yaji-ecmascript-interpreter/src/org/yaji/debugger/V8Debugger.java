package org.yaji.debugger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaji.json.JsonUtil;

import FESI.Data.ESBoolean;
import FESI.Data.ESNull;
import FESI.Data.ESNumber;
import FESI.Data.ESObject;
import FESI.Data.ESString;
import FESI.Data.ESUndefined;
import FESI.Data.ESValue;
import FESI.Data.ObjectObject;
import FESI.Exceptions.EcmaScriptException;
import FESI.Interpreter.Evaluator;
import FESI.Interpreter.Version;

public class V8Debugger {

    private abstract class CommandHandler {
        public abstract void invoke(ESObject jsonValue, ESObject response) throws EcmaScriptException;
    }
    private static class SourceFile {
        private String text = "";
        private int nLines = 0;

        public SourceFile(File file) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for(String readline = in.readLine(); readline != null; readline = in.readLine()) {
                    sb = sb.append(readline).append("\r\n");
                    nLines ++;
                }
                in.close();
                
                text = sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public ESValue getText() {
            return new ESString(text);
        }
        
        public ESValue getLineCount() {
            return ESNumber.valueOf(nLines);
        }
    }

    ESObject createArray() throws EcmaScriptException {
        return evaluator.createArray();
    }
    
    private Map<String,CommandHandler> commandMap = new HashMap<String,CommandHandler>() {
        private static final long serialVersionUID = 1L;
        {
            put("version",new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) throws EcmaScriptException {
                    response.putProperty("success", ESBoolean.valueOf(true));
                    ESObject body = ObjectObject.createObject(evaluator);
                    body.putProperty("V8Version", ESString.valueOf(Version.Level));
                    response.putProperty("body", body);
                    response.putProperty("running", ESBoolean.valueOf(debugger.isPaused()));
                }
            });
            put("continue", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) throws EcmaScriptException {
                    response.putProperty("success", ESBoolean.valueOf(true));
                    response.putProperty("running", ESBoolean.valueOf(true));
                }
            });
            put("evaluate", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) throws EcmaScriptException {
                    ESObject arguments = (ESObject) request.getProperty("arguments","arguments".hashCode());
                    String expression = arguments.getProperty("expression","expression".hashCode()).toString();
                    boolean noException = true;
                    ESValue result = ESNull.theNull;
                    try {
                        result = evaluator.evaluate(expression);
                        response.putProperty("body", new ESString(JsonUtil.stringify(evaluator,result,ESUndefined.theUndefined, ESUndefined.theUndefined)));
                    } catch (EcmaScriptException e) {
                        e.printStackTrace();
                        noException = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                        noException = false;
                    }
                    
                    response.putProperty("running", ESBoolean.valueOf(!debugger.isPaused()));
                    response.putProperty("succcess", ESBoolean.valueOf(noException));
                }
            });
            put("lookup", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("backtrace", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("frame", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("scope", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("scopes", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("scripts", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) throws EcmaScriptException {
                    ESObject arguments = (ESObject) request.getProperty("arguments","arguments".hashCode());
                    boolean includeSource = arguments.getProperty("includeSource","includeSource".hashCode()).booleanValue();
                    ESObject array = evaluator.createArray();
                    List<File> scripts = debugger.getScripts();
                    int index = 1;
                    for (File file : scripts) {
                        ESObject script = ObjectObject.createObject(evaluator);
                        script.putProperty("id", ESNumber.valueOf(index++), "id".hashCode());
                        script.putProperty("name", new ESString(file.getName()), "name".hashCode());
                        SourceFile sourceFile = new SourceFile(file);
                        script.putProperty("lineCount", sourceFile.getLineCount(), "lineCount".hashCode());
                        if (includeSource) {
                            script.putProperty("source", sourceFile.getText(), "source".hashCode());
                        }
                        array.putProperty((long)index,script);
                    }
                    
                    response.putProperty("body", array, "body".hashCode());
                    response.putProperty("running", ESBoolean.valueOf(!debugger.isPaused()));
                    response.putProperty("success", ESBoolean.valueOf(true));
                }
            });
            put("source", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
}
            });
            put("setbreakpoint", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("changebreakpoint", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("clearbreakpoint", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("setexceptionbreak", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("v8flags", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("profile", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("disconnect", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("gc", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) {
                    // TODO
                }
            });
            put("listbreakpoints", new CommandHandler() {
                @Override
                public void invoke(ESObject request, ESObject response) throws EcmaScriptException {
                    ESObject object = ObjectObject.createObject(evaluator);
                    object.putProperty("breakpints", createArray(), "breakpoints".hashCode());
                    object.putProperty("breakOnExceptions", ESBoolean.valueOf(true), "breakOnExceptions".hashCode());
                    object.putProperty("breakOnUncaughtExceptions", ESBoolean.valueOf(true), "breakOnUncaughtExceptions".hashCode());
                    response.putProperty("body", object, "body".hashCode());
                    response.putProperty("running", ESBoolean.valueOf(!debugger.isPaused()));
                    response.putProperty("succcess", ESBoolean.valueOf(true));
                }
            });
        }
    };
    private final Debugger debugger;
    private final Evaluator evaluator;
    private int seq;

    public V8Debugger(Debugger debugger,Evaluator evaluator) {
        this.debugger = debugger;
        this.evaluator = evaluator;
    }

    public void handle(ESObject request, Result result) throws EcmaScriptException {
        String commandType = request.getProperty("command","command".hashCode()).toString();
        CommandHandler handler = commandMap.get(commandType);
        ESObject response = ObjectObject.createObject(evaluator);
        response.putProperty("seq", ESNumber.valueOf(seq++));
        response.putProperty("request_seq", request.getProperty("seq","seq".hashCode()));
        response.putProperty("type", ESString.valueOf("response"));
        response.putProperty("command", ESString.valueOf(commandType));
        if (handler != null) {
            handler.invoke(request, response);
            result.setCode(V8ResultCode.OK);
        } else {
            result.setCode(V8ResultCode.UNKNOWN_COMMAND);
        }
        result.setData(response);
    }

}
