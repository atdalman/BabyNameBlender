package io.randomizer.name_blender.service;

import eu.crydee.syllablecounter.SyllableCounter;
import io.randomizer.name_blender.model.FullName;
import io.randomizer.name_blender.model.WeightedName;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NameBlenderServiceImpl implements NameBlenderService {

    private final SyllableCounter sc = new SyllableCounter();

    @Override
    @Scheduled(fixedDelay = 5)
    public void blendName() throws FileNotFoundException {
        File file = new File("C:\\Users\\atdal\\IdeaProjects\\name-blender\\name-blender\\src\\main\\resources\\names");
        Scanner input = new Scanner(file);
        ArrayList<WeightedName> nameInput = new ArrayList<>();
        HashMap<String, List<FullName>> nameOutput = new HashMap<>();
        final WeightedName surname = new WeightedName("Dalman", 0, 2);

        while (input.hasNext()) {
            final String name = input.next();
            final Integer weight = input.nextInt();
            final Integer potentialSyllableCount = input.nextInt();
            final Integer syllableCount = potentialSyllableCount == 0 ? sc.count(name) : potentialSyllableCount;

            WeightedName weightedName = new WeightedName(name, weight, syllableCount);
            nameInput.add(weightedName);
        }

        for (int i = 0; i < nameInput.size(); i++) {
            for (int j = i + 1; j < nameInput.size(); j++) {

                FullName fn = new FullName(nameInput.get(i), nameInput.get(j), surname);
                if (matchesRhythm(2, 3, 2, fn)){
                    nameOutput = addNameToMap(fn, nameOutput);
                }
                if (matchesRhythm(3, 2, 2, fn)){
                    nameOutput = addNameToMap(fn, nameOutput);
                }

                fn = new FullName(nameInput.get(j), nameInput.get(i), surname);
                if (matchesRhythm(2, 3, 2, fn)){
                    nameOutput = addNameToMap(fn, nameOutput);
                }
                if (matchesRhythm(3, 2, 2, fn)){
                    nameOutput = addNameToMap(fn, nameOutput);
                }
            }
        }

        nameOutput.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            entry.getValue().forEach(name -> System.out.println("\t" + name));
        });
    }

    private HashMap<String, List<FullName>> addNameToMap(FullName fn, HashMap<String, List<FullName>> nameMap) {
        final String firstLast = fn.first().name() + " " + fn.surname().name();
        if (!nameMap.containsKey(firstLast)) {
            ArrayList<FullName> nameList = new ArrayList<>();
            nameList.add(fn);
            nameMap.put(firstLast, nameList);
        } else {
            List<FullName> existingNames = nameMap.get(firstLast);
            existingNames.add(fn);
            Collections.sort(existingNames);
            nameMap.put(firstLast, existingNames);

        }

        return nameMap;
    }

    private boolean matchesRhythm(int firstSyll, int midSyll, int surSyll, FullName fullName) {
        boolean matches = true;

        if(fullName.first().syllableCount() != firstSyll) {
            matches = false;
        } else if (fullName.middle().syllableCount() != midSyll) {
            matches = false;
        } else if (fullName.surname().syllableCount() != surSyll) {
            matches = false;
        }

        return matches;
    }
}
