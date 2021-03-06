package com.multimif.util;


import javax.json.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author p1317074
 * @version 1.0
 * @since 1.0 16/11/16.
 */
public class ArboTree {

    ArboNode root;
    ArboNode commonRoot;

    public ArboTree(ArboNode root) {
        this.root = root;
        commonRoot = null;
    }

    public void addElement(String elementValue) {
        String[] list = elementValue.split("/");

        // latest element of the list is the filename.extension
        root.addElement(root.incrementalPath, list);

    }

    public boolean existElement(String path) {
        String[] list = path.split("/");
        return root.existElement(list, 0);
    }

    public void printTree() {
        //I move the tree common root to the current common root because I don't mind about initial folder
        //that has only 1 child (and no leaf)
        getCommonRoot();
        commonRoot.printNode(0);
    }

    public JsonObject toJson() {
        //StringWriter sw = new StringWriter();
        Map<String, Object> config = new HashMap<String, Object>();
        //config.put(JsonGenerator.PRETTY_PRINTING, true);
        //JsonWriterFactory jf = Json.createWriterFactory(config);
        //JsonWriter jw = jf.createWriter(sw);

        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonArrayBuilder root = factory.createArrayBuilder();
        getCommonRoot();

        commonRoot.toJson(root, factory);
        JsonObject ret = factory.createObjectBuilder().add("root", root).build();
        //jw.writeObject(ret);
        //jw.close();

        //System.out.println(sw.toString());
        return ret;
    }

    public ArboNode getCommonRoot() {
        if (commonRoot != null)
            return commonRoot;
        else {
            ArboNode current = root;
            while (current.leafs.size() <= 0) {
                current = current.childs.get(0);
            }
            commonRoot = current;
            return commonRoot;
        }

    }

}