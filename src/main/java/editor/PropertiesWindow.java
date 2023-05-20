package editor;

import components.Component;
import components.NonPickable;
import components.SpriteRenderer;
import imgui.ImGui;
import jade.GameObject;
import jade.MouseListener;
import org.joml.Vector4f;
import physics2d.components.Box2DCollider;
import physics2d.components.CircleCollider;
import physics2d.components.MoveContollable;
import physics2d.components.Rigidbody2D;
import renderer.PickingTexture;
import scenes.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class PropertiesWindow {
    private List<GameObject> activeGameObjects;
    private GameObject activeGameObject = null;
    private GameObject MasterObject=new GameObject("MasterObject");
    private PickingTexture pickingTexture;

    public PropertiesWindow(PickingTexture pickingTexture) {
        this.activeGameObjects = new ArrayList<>();
        this.pickingTexture = pickingTexture;
    }

    public void imgui() {
        if (activeGameObjects.size() > 0 && activeGameObjects.get(0) != null) {
            ImGui.begin("Properties");

            if (ImGui.beginPopupContextWindow("ComponentAdder")) {
                if (ImGui.menuItem("Add MoveControllable")) {
                    for (GameObject go : activeGameObjects) {
                        if (go.getComponent(MoveContollable.class) == null) {
                            go.addComponent(new MoveContollable());
                        }
                    }
                }
                if (ImGui.menuItem("Add Rigidbody")) {
                    for (GameObject go : activeGameObjects) {
                    if (go.getComponent(Rigidbody2D.class) == null) {
                        go.addComponent(new Rigidbody2D());
                    }
                    }
                }

                if (ImGui.menuItem("Add Box Collider")) {
                    for (GameObject go : activeGameObjects) {
                        if (go.getComponent(Box2DCollider.class) == null) {

                            if (go.getComponent(CircleCollider.class) != null) {
                                go.removeComponent(CircleCollider.class);
                            }
                        }
                        go.addComponent(new Box2DCollider());
                    }
                }


                if (ImGui.menuItem("Add Circle Collider")) {
                    for (GameObject go : activeGameObjects) {
                        if (go.getComponent(CircleCollider.class) == null) {
                            if (go.getComponent(Box2DCollider.class) != null) {
                                go.removeComponent(Box2DCollider.class);
                            }
                            go.addComponent(new CircleCollider());
                        }
                    }
                }

                ImGui.endPopup();
            }
            if (activeGameObjects.size() ==1){
                activeGameObjects.get(0).imgui();
            }else{
                activeGameObjects=MasterObject.masterGui(activeGameObjects);


            }

            ImGui.end();
        }
    }

    public GameObject getActiveGameObject() {
        return activeGameObjects.size() == 1 ? this.activeGameObjects.get(0) :
                null;
    }

    public List<GameObject> getActiveGameObjects() {
        return this.activeGameObjects;
    }

    public void clearSelected() {
        this.MasterObject=new GameObject("MasterObject");
        this.activeGameObjects.clear();
    }

    public void setActiveGameObject(GameObject go) {

        if (go != null) {
            clearSelected();
            this.activeGameObjects.add(go);
        }
    }

    public void addActiveGameObject(GameObject go) {
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);

        this.activeGameObjects.add(go);
        MasterObject=go.mengui(MasterObject);
    }

    public PickingTexture getPickingTexture() {
        return this.pickingTexture;
    }
}
