package editor;

import components.NonPickable;
import components.SpriteRenderer;
import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import jade.GameObject;
import jade.MouseListener;
import observers.EventSystem;
import observers.events.Event;
import observers.events.EventType;
import org.joml.Vector4f;
import physics2d.components.Box2DCollider;
import physics2d.components.CircleCollider;
import physics2d.components.Rigidbody2D;
import renderer.PickingTexture;
import scenes.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class Menu {
    private List<GameObject> activeGameObjects;
    private List<Vector4f> activeGameObjectsOgColor;
    private GameObject activeGameObject = null;
    private PickingTexture pickingTexture;

    public Menu(PickingTexture pickingTexture) {
        this.activeGameObjects = new ArrayList<>();
        this.pickingTexture = pickingTexture;
        this.activeGameObjectsOgColor = new ArrayList<>();
    }

    public void imgui() {
        if (activeGameObjects.size() == 1 && activeGameObjects.get(0) != null) {
            activeGameObject = activeGameObjects.get(0);
            imgui.ImGuiIO io = ImGui.getIO();
            ImGui.setNextWindowSize(io.getDisplaySizeX(),io.getDisplaySizeY()/4);
            ImGui.setNextWindowPos(0,io.getDisplaySizeY()*3/4);
            ImGui.begin("Menu" , ImGuiWindowFlags.MenuBar| ImGuiWindowFlags.NoResize
                    | ImGuiWindowFlags.NoTitleBar| ImGuiWindowFlags.NoCollapse| ImGuiWindowFlags.NoDecoration);
            if (ImGui.menuItem("Add Circle Collider")) {
                if (activeGameObject.getComponent(CircleCollider.class) == null){
                    if (activeGameObject.getComponent(Box2DCollider.class) != null) {
                        activeGameObject.removeComponent(Box2DCollider.class);
                    }
                    activeGameObject.addComponent(new CircleCollider());
                }
            }
            activeGameObject.imgui();
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
        if (activeGameObjectsOgColor.size() > 0) {
            int i = 0;
            for (GameObject go : activeGameObjects) {
                SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
                if (spr != null) {
                    spr.setColor(activeGameObjectsOgColor.get(i));
                }
                i++;
            }
        }
        this.activeGameObjects.clear();
        this.activeGameObjectsOgColor.clear();
    }

    public void setActiveGameObject(GameObject go) {
        if (go != null) {
            clearSelected();
            this.activeGameObjects.add(go);
        }
    }

    public void addActiveGameObject(GameObject go) {
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        if (spr != null ) {
            this.activeGameObjectsOgColor.add(new Vector4f(spr.getColor()));
            spr.setColor(new Vector4f(0.8f, 0.8f, 0.0f, 0.8f));
        } else {
            this.activeGameObjectsOgColor.add(new Vector4f());
        }
        this.activeGameObjects.add(go);
    }

    public PickingTexture getPickingTexture() {
        return this.pickingTexture;
    }
}
