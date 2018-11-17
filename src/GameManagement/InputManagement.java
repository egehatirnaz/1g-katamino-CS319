package GameManagement;

/*
  Input Management

  This component receives the user mouse input and takes the input information to Level Management to put the pieces into the puzzle.

  Ege Hatırnaz
  07.11.2018
 */

import java.util.*;
import java.lang.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

