package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipiesForSupplies {

  Map<String, Integer> recipeIds = new HashMap<>();
  Set<String> supply = new HashSet<>();
  Boolean[] canAdd;

  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    for (int i = 0; i < recipes.length; i++) {
      recipeIds.put(recipes[i], i);
    }
    canAdd = new Boolean[recipes.length];
    for (int i = 0; i < supplies.length; i++) {
      supply.add(supplies[i]);
    }
    for (int i = 0; i < recipes.length; i++) {
      if (canAdd[i] == null) {
        dfs(i, recipes[i], ingredients);
      }
    }
    List<String> result = new ArrayList<>();
    for (int i = 0; i < canAdd.length; i++) {
      if (canAdd[i]) {
        result.add(recipes[i]);
      }
    }
    return result;
  }

  private void dfs(int id, String rec, List<List<String>> ingredients) {
    if (canAdd[id] != null) {
      return;
    }
    canAdd[id] = Boolean.FALSE;
    List<String> ingrs = ingredients.get(id);
    for (int i = 0; i < ingrs.size(); i++) {
      String ing = ingrs.get(i);
      if (supply.contains(ing)) {
        continue;
      }
      Integer idIng = recipeIds.get(ing);
      if (idIng == null) {
        return;
      }
      dfs(idIng, ing, ingredients);
      if (!canAdd[idIng]) {
        return;
      }
    }
    canAdd[id] = Boolean.TRUE;
    supply.add(rec);
  }
}
