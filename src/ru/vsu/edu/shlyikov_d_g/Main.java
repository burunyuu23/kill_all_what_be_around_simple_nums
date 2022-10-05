package ru.vsu.edu.shlyikov_d_g;

import ru.vsu.edu.shlyikov_d_g.util.SwingUtils;

import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.addFirst(20);
        list.addFirst(19);
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(10);
        list.addFirst(13);
        list.addFirst(30);
        list.addFirst(32);
        list.addFirst(40);
        list.addFirst(11);
        list.addFirst(11);
        list.addFirst(10);
        list.addFirst(11);
        list.addFirst(9);
        list.addFirst(9);
        list.addFirst(9);
        list.addFirst(9);
        list.addFirst(11);
        list.addFirst(9);

        выводТекста(list);

        SimpleLinkedList.task(list);

        выводТекста(list);

        winMain();

    }

    public static void winMain(){
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Minecraft Rus", 1,18);
        //SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
        // 0 - Plain
        // 1 - Bold
        // 2 - Italic
        // 3 - Bold+Italic
    }

    public static void выводТекста(SimpleLinkedList<Integer> list) throws SimpleLinkedList.SimpleLinkedListException {
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();
        System.out.println();
    }
}
